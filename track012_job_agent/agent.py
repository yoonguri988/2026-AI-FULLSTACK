import os      # 운영체제관련
import random  # 대기시간 등 임의의 랜던값 모듈
import re      # 정규식 사용
import time    # 시간지연 (sleep)
from urllib.parse import quote  # url 한글검색어 인코딩

from playwright.sync_api import sync_playwright   # playwright  동기 api 불러오기

# ver-1   SEARCH_KEYWORD = "JAVA SPRING 개발자"

# ==================== [ 검색 조건 설정 ] ====================
SEARCH_KEYWORD = "JAVA SPRING 개발자"  # ★ 검색키워드 설정

# 지역 코드 (서울: 101000, 경기: 102000, 인천: 108000)
# (전국으로 하려면 LOC_CODE = "" 로 설정)
LOC_CODE = "101000"  # 서울

# 경력 조건 (최소/최대 연차)
EXP_MIN = 0  # 1년차부터   최소경력
EXP_MAX = 1  # 3년차까지   최대경력
# ============================================================

# True: 테스트 모드 (클릭 대기만 함)
# False: 실제 클릭 제출
DRY_RUN = False  # 실제 제출안누르고 탐색/확인  
MAX_APPLY = 3  # 한 번 실행 시 처리할 최대 공고 수  ★

BASE_DIR = os.path.dirname(os.path.abspath(__file__))  # 현재 실행중인 절대경로
STATE_PATH = os.path.join(BASE_DIR, "state.json")   # 로그인 세션정보 state.json
DEBUG_DIR = os.path.join(BASE_DIR, "debug")  # 실패시 캡쳐스크린샷 저장할 debug 경로
os.makedirs(DEBUG_DIR, exist_ok=True)  # debug 폴더 새로생성

# ★사람채용목록의 공고카드 css
CARD_SELECTORS = [".item_recruit", "div[class*='item_job']", "li[class*='item']"]


def get_cards(page):
    """공고 카드 locator 반환"""
    for sel in CARD_SELECTORS:  #  공고카드 css 셀렉터 확인
        loc = page.locator(sel) # 일치하는 요소탐색
        if loc.count() > 0:  # 일치하는 요소가 1개 이상존재
            return loc, sel  # 선택한거, 셀렉터
    return page.locator(CARD_SELECTORS[0]), CARD_SELECTORS[0]   # 못찾으면 기본값 첫번째 반환


def close_apply_layer(page):
    """우측 입사지원 레이어를 확실하게 닫아서 화면 가림 방지"""
    try:
        # 1. ESC 키 누르기
        page.keyboard.press("Escape")
        time.sleep(0.5) # 0.5초 대기

        # 2. X 닫기 버튼 직접 클릭
        close_btns = page.locator(  # 다양한 닫기버튼 셀렉터 저장
            "#quick_apply_layer .btn_close, .iframe_layer .btn_close, button[title='닫기'], a:has-text('닫기')"
        )
        for i in range(close_btns.count()):  # 찾아낸 닫기 버튼 갯수만큼
            btn = close_btns.nth(i)  # 닫기버튼찾아와기
            if btn.is_visible():    # 버튼이 화면에 보이면
                btn.click(force=True) # 강제클릭 닫아주기
                time.sleep(0.5)  # 0.5초 대기

        # 3. 레이어 element 강제 제거 (JS)
        page.evaluate("""
            () => {
                const layers = document.querySelectorAll('#quick_apply_layer, .iframe_layer, #iframe_layer, #quick_apply_frame');
                layers.forEach(el => el.remove());
            }
        """)  # 자바스크립트 직접 실행해서 다른지원레이어 완전히 닫기
        time.sleep(0.5)
    except Exception:
        pass   # 에러나더라도 무시하고 진행


def find_apply_button_in_card(card):
    """목록 카드 내부의 작은 지원 버튼 탐색"""
    candidates = [  # 지원버튼 찾기 위한 css 후보셀렉터
        card.locator("a.sri_btn_immediately, button.sri_btn_immediately"),
        card.locator("a[class*='btn_apply'], button[class*='btn_apply']"),
        card.locator("a:has-text('입사지원'), button:has-text('입사지원')"),
        card.locator("a:has-text('즉시지원'), button:has-text('즉시지원')"),
    ]
    for loc in candidates:  # 후보셀렉터 반복
        try:
            for k in range(loc.count()):  # 검색된 요소 수만큼 반복
                el = loc.nth(k)  # k버째 버튼 요소
                if el.is_visible():  # 화면에 노출이 되어 있으면 
                    txt = (el.inner_text() or "").strip()  # 텍스트추출, 공백제거
                    if ("지원" in txt or "입사" in txt) and not any(kw in txt for kw in ["완료", "마감", "보기"]):
                        return el  # 조건에 맞는 버튼만 요소반환
        except Exception:
            continue
    return None  # 지원버튼 못찾으면 None 반환

def click_red_layer_submit_button(context):
    """
    우측 레이어(또는 iframe) 내부의 배경이 빨간색/주황색인 진짜 제출 버튼만 정확하게 타깃팅
    """
    targets = []   # 페이지 리스트
    for pg in context.pages:
        targets.append(pg)   # 페이지 추가
        for fr in pg.frames:
            targets.append(fr)  

    submit_selectors = [  # 레이어 내부의 최종지원 제출버튼 셀렉터 목록
        "#quick_apply_layer button.btn_apply",
        "#quick_apply_layer .btn_apply",
        "div[class*='quick_apply'] button:has-text('입사지원')",
        "div[class*='layer'] button:has-text('입사지원')",
        "button[class*='btn_apply']",
        "#btn_apply",
        "button:has-text('입사지원')",
    ]

    for target in targets:  # 페이지/iframe 탐색
        for sel in submit_selectors:  # 셀렉터별로 탐색
            try:
                btn_loc = target.locator(sel)  # 버튼 요소 지정
                cnt = btn_loc.count()  # 찾아낸 버튼 갯수
                for i in range(cnt):
                    btn = btn_loc.nth(i)
                    if btn.is_visible():  # 실제로 화면에 보이는지 확인
                        box = btn.bounding_box()
                        # 화면 오른쪽 레이어 영역에 있는 지 확인 (X 좌표가 400px 이상)
                        if box and box["x"] > 400 and box["width"] > 100:
                            txt = (btn.inner_text() or "").strip()
                            if "보기" not in txt and "입사지원" in txt:
                                if DRY_RUN:
                                    print(f"  🎉 [드라이런] 우측 레이어 빨간색 제출 버튼 발견! (txt: '{txt}')")
                                    return True
                                else:
                                    btn.click(force=True)  ## 입사지원 최종제출 버튼 강제클릭
                                    print(f"  🚀 우측 레이어 빨간색 입사지원 버튼 클릭 성공! (txt: '{txt}')")
                                    return True  # 성공
            except Exception:
                continue

    return False  # 버튼 못찾았을때 


def run_agent():
    #1. 로그인 정보 유효성검사
    if not os.path.exists(STATE_PATH):
        print("⚠️ state.json 파일이 없습니다. save_login.py를 먼저 실행해 주세요.")
        return

    #2. playwright 브라우저 실행
    with sync_playwright() as p:
        browser = p.chromium.launch(
            channel="chrome",
            headless=False,
            args=["--disable-blink-features=AutomationControlled", "--start-maximized"],
        )
        # 저장된 로그인 쿠키/세션( state.json ) 로드해서 컨텍스트 생성 
        context = browser.new_context(storage_state=STATE_PATH, no_viewport=True)
        page = context.new_page()  # 새탭 생성

        #3. 사람인 검색url 생성 및 이동 ★
        search_url = (
            "https://www.saramin.co.kr/zf_user/search/recruit"
            f"?searchword={quote(SEARCH_KEYWORD)}"
        )
        print(f"[{SEARCH_KEYWORD}] 사람인 채용 검색 페이지로 이동 중...")
        page.goto(search_url)  # 검색 url 이동
        time.sleep(2)  # 페이지 로딩 대기

        #4. 로그인 세션 유지 상태확인
        login_btn = page.query_selector("a:has-text('로그인')")
        if login_btn and login_btn.is_visible():
            print("⚠️ 세션이 만료되었습니다. 로그인을 다시 진행해 주세요.")
            input("엔터 키를 누르면 종료합니다...")
            browser.close()
            return
        print("✅ 로그인 상태가 정상 확인되었습니다.")

        #5. 공고 카드 요소 로딩 대기 및ㅇ 탐색
        print("⏳ 채용 공고 목록 스캔 중...")
        try: # 설정카드 나타날때 까지 최대 10초 대기
            page.wait_for_selector(",".join(CARD_SELECTORS), state="attached", timeout=10000)
        except Exception:
            pass

       # 공고카드가드가져오기     
        cards, card_sel = get_cards(page)
        card_count = cards.count()  # 갯수 카운드
        print(f"🔍 총 {card_count}개 카드 스캔 완료.")

        mode = "드라이런(테스트 모드)" if DRY_RUN else "🔥 실제 입사지원 모드"
        print(f"▶ 현재 모드: {mode} (최대 {MAX_APPLY}개 실행)\n")

        processed = 0  # 시도/지원한 공고수 카운터

        #6. 각 공고 카드를 돌면서 지원 진행
        for i in range(card_count):
            if processed >= MAX_APPLY:   # ★ 설정한 최대 지원수에 도달 중단
                print(f"🎯 설정 수량({MAX_APPLY}개) 완료로 종료합니다.")
                break

            try:
                # 잔여 레이어 팝업 깔끔히 정리
                close_apply_layer(page)

                current_cards, _ = get_cards(page)
                if i >= current_cards.count():
                    break

                card = current_cards.nth(i)   # 해당번호 카드 지정

                # 해당공고카드가 화면에 오도록 스크롤이동    
                try:
                    card.evaluate("el => el.scrollIntoView({block: 'center'})")
                    time.sleep(0.3)
                except Exception:
                    continue
                # 공고카드안에 입사지원 버튼찾기    
                list_btn = find_apply_button_in_card(card)
                if not list_btn:
                    continue
                # 지원카운트 1증가    
                processed += 1
                print(f"[{processed}/{MAX_APPLY}] 카드 #{i} 목록 지원 버튼 클릭...")

                # force=True로 다른 레이어가 일부 남아있어도 클릭 강제 실행
                list_btn.click(force=True)
                time.sleep(2.5)  # 레이어 로딩 대기

                # 우측 레이어 제출 버튼 클릭
                success = click_red_layer_submit_button(context)

                if success:
                    if not DRY_RUN:
                        time.sleep(2.5)
                        print("  🎉 입사지원 제출 완료!")
                else:
                    print("  ⚠️ 우측 레이어 내부의 빨간색 제출 버튼을 찾지 못함")
                    page.screenshot(path=os.path.join(DEBUG_DIR, f"fail_{processed}.png"))

                # 완료 후 팝업 완전히 닫기
                close_apply_layer(page)
                time.sleep(random.uniform(1.5, 2.5))   # 빠른요청으로 인한 탐지막기 랜덤대기

            except Exception as e:
                print(f"  ⚠️ 오류 발생: {e}")
                close_apply_layer(page)  # 에러발생시 팝업닫고 다음진행
                continue
        # 7. 종료처리        
        print("\n✨ 모든 입사지원 작업이 완료되었습니다.")
        input("엔터 키를 누르면 브라우저를 종료합니다...")
        browser.close()  # 브라우저 종료

#### 스크립트 실행시 메인함수
if __name__ == "__main__":
    run_agent()

# 미션1) 조건검색 - 년차1~3 / 지역 : 서울, 인천, 경기
# 미션2) 여러개 지원가능하게     