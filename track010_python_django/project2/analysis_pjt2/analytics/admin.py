from django.contrib import admin
from .models import ServiceLog

# ServiceLogAdmin 클래스 정의 및 등록
@admin.register(ServiceLog)
class ServiceLogAdmin(admin.ModelAdmin): 
    list_display = ('date', 'category', 'visitor_count', 'sales_amount')