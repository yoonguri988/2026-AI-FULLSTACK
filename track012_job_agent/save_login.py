import os
from playwright.sync_api import sync_playwright

BASE_DIR = os.path.dirname(os.path.abspath(__file__))   # 현재 실행중인 파이썬 파일 경로
STATE_PATH = os.path.join(BASE_DIR, "state.json")   # 로그인 세션 데이터 저장

with sync_playwright() as p:   # 사용후에 반드시 닫기 or 해제가 필요한 자원
    # 1. 일반 크롬 브라우저 실행
    browser = p.chromium.launch(
        channel="chrome",  # 크롬브라우저이용
        headless=False,  # 실제 브라우저 띄우기 ★
        args=["--disable-blink-features=AutomationControlled", "--start-maximized"]
    )
    context = browser.new_context(no_viewport=True)
    page = context.new_page()

    page.goto("https://www.saramin.co.kr/zf_user/auth")   # ★ 잡코리아 로그인경로

    print("\n==================================================")
    print("1. 브라우저에서 사람인 로그인(소셜 로그인 포함)을 완료하세요.")
    print("2. 로그인 완료 후 사람인 메인 화면이 정상적으로 나오면 Enter를 누르세요.")
    print("==================================================\n")
    input("로그인 완료 후 Enter를 누르세요: ")

    # 2. 쿠키 + LocalStorage 등 세션 전체를 state.json 파일로 공식 저장
    context.storage_state(path=STATE_PATH)
    print(f"✅ 로그인 세션이 성공적으로 저장되었습니다: {STATE_PATH}")
    
    browser.close()


# ;  {}   →  들여쓰기로 영역
# if   조건 :     , for i in range :
