# analytics/urls.py
from django.urls import path
from . import views

urlpatterns = [
    # http://127.0.0.1:8000/dashboard/ 요청시 views.py 의 dashboard_view 연결
    # 기본경로, 해결사:처리.dashboard_view, 이름
    path('', views.dashboard_view, name='dashboard'),
        # http://127.0.0.1:8000/dashboard/api/statistics/ 요청시 views.py 의 api_receive_statistics 연결
    path('api/statistics/', views.api_receive_statistics, name='api_receive_statistics'),
]