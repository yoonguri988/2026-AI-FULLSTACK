import json
import requests
import pandas as pd
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import ServiceLog

def dashboard_view(request):
    spring_api_url = "http://localhost:8080/api/statistics/sync"

    try:
        response = requests.post(spring_api_url, timeout=3)
        if response.status_code == 200:
            print("✅ 스프링 부트로부터 통계 데이터 동기화 성공!")
        else:
            print(f"⚠️ 스프링 부트 응답 코드: {response.status_code}")
    except Exception as e:
        print(f"❌ 스프링 부트 서버 연결 실패: {e}")

    qs = ServiceLog.objects.all().values('date', 'category', 'visitor_count', 'sales_amount')

    if qs.exists():
        df = pd.DataFrame(list(qs))

        summary_df = df.groupby('category')[['visitor_count', 'sales_amount']].sum().reset_index()

        total_visitors = summary_df['visitor_count'].sum()
        if total_visitors > 0:
            summary_df['visitor_share'] = (summary_df['visitor_count'] / total_visitors * 100).round(1)
        else:
            summary_df['visitor_share'] = 0

        stats_summary = {
            'avg_visitors': round(df['visitor_count'].mean(), 1),
            'max_visitors': int(df['visitor_count'].max()),
            'min_visitors': int(df['visitor_count'].min()),
            'total_count': int(df['visitor_count'].sum()),
        }

        top_category_idx = summary_df['visitor_count'].idxmax()
        top_category = summary_df.loc[top_category_idx, 'category']

        categories = summary_df['category'].tolist()
        visitors = summary_df['visitor_count'].tolist()
        sales = summary_df['sales_amount'].tolist()
        shares = summary_df['visitor_share'].tolist()

    else:
        categories, visitors, sales, shares = [], [], [], []
        stats_summary = {'avg_visitors': 0, 'max_visitors': 0, 'min_visitors': 0, 'total_count': 0}
        top_category = "데이터 없음"

    context = {
        'categories': categories,
        'visitors': visitors,
        'sales': sales,
        'shares': shares,
        'stats_summary': stats_summary,
        'top_category': top_category,
    }
    return render(request, 'analytics/dashboard.html', context)


@csrf_exempt
def api_receive_statistics(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            
            log_date = data.get('date')
            category = data.get('category', '커뮤니티')
            count_val = data.get('count', 0)
            
            ServiceLog.objects.update_or_create(
                date=log_date,
                category=category,
                defaults={
                    'visitor_count': count_val,
                    'sales_amount': 0
                }
            )
            
            return JsonResponse({'status': 'success', 'message': '통계 데이터 갱신 완료!'})
        except Exception as e:
            return JsonResponse({'status': 'error', 'message': str(e)}, status=400)
            
    return JsonResponse({'status': 'fail', 'message': 'POST 요청만 지원합니다.'}, status=405)