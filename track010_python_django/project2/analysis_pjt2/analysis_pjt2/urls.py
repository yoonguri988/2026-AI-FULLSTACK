"""
URL configuration for analysis_pjt2 project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/6.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
# analysis_pjt/urls.py
from django.contrib import admin
from django.urls import path , include
from django.views.generic import RedirectView

urlpatterns = [
    # Admin 관리자 라우팅
    path('admin/', admin.site.urls),
    # /dashboard/ 주소로 들어오는 애들
    path('dashboard/', include('analytics.urls')),

    # http://127.0.0.1:8000 접속시, /dashboard/로 자동이동
    path('', RedirectView.as_view(url='/dashboard/', permanent=False)),
]