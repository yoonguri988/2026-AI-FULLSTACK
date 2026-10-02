### 1단계: 개발 환경 구성 (Python 및 Playwright 설치)

> 컴퓨터의 터미널(명령 프롬프트)을 열고 자동화에 필요한 파이썬 라이브러리를 설치합니다.

```bash
pip install playwright
playwright install chromium

```

1) playwright - 웹크롤링, 웹브라우저 제어
2) chromium - 동적 웹크롤링

※ 작동확인 
```
npx  playwright open  http://naver.com
```

### 2단계:  자동 로그인 세션(쿠키) 만들기
> save_login.py

```bash
import os
from playwright.sync_api import sync_playwright

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
STATE_PATH = os.path.join(BASE_DIR, "state.json")

with sync_playwright() as p:
    # 1. 일반 크롬 브라우저 실행
    browser = p.chromium.launch(
        channel="chrome",
        headless=False,
        args=["--disable-blink-features=AutomationControlled", "--start-maximized"]
    )
    context = browser.new_context(no_viewport=True)
    page = context.new_page()

    page.goto("https://www.saramin.co.kr/zf_user/auth")

    print("\n==================================================")
    print("1. 브라우저에서 사람인 로그인(소셜 로그인 포함)을 완료하세요.")
    print("2. 로그인 완료 후 사람인 메인 화면이 정상적으로 나오면 Enter를 누르세요.")
    print("==================================================\n")
    input("로그인 완료 후 Enter를 누르세요: ")

    # 2. 쿠키 + LocalStorage 등 세션 전체를 state.json 파일로 공식 저장
    context.storage_state(path=STATE_PATH)
    print(f"✅ 로그인 세션이 성공적으로 저장되었습니다: {STATE_PATH}")
    
    browser.close()
```

1. 실행하고
2. 로그인한다음
3. 메인페이지로이동
4. 콘솔창으로 와서 enter 
5. 로그인 정보 기록이 state.json 에 남음


### 3. 키워드에 맞춘 지원에이젼트 작성
> agent.py

```bash
import os
import random
import re
import time
from urllib.parse import quote

from playwright.sync_api import sync_playwright

# ver-1   SEARCH_KEYWORD = "JAVA SPRING 개발자"

# ==================== [ 검색 조건 설정 ] ====================
SEARCH_KEYWORD = "JAVA SPRING 개발자"

# 지역 코드 (서울: 101000, 경기: 102000, 인천: 108000)
# (전국으로 하려면 LOC_CODE = "" 로 설정)
LOC_CODE = "101000"  # 서울

# 경력 조건 (최소/최대 연차)
EXP_MIN = 1  # 1년차부터
EXP_MAX = 3  # 3년차까지
# ============================================================

# True: 테스트 모드 (클릭 대기만 함)
# False: 실제 클릭 제출
DRY_RUN = False
MAX_APPLY = 5  # 한 번 실행 시 처리할 최대 공고 수

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
STATE_PATH = os.path.join(BASE_DIR, "state.json")
DEBUG_DIR = os.path.join(BASE_DIR, "debug")
os.makedirs(DEBUG_DIR, exist_ok=True)

CARD_SELECTORS = [".item_recruit", "div[class*='item_job']", "li[class*='item']"]


def get_cards(page):
    """공고 카드 locator 반환"""
    for sel in CARD_SELECTORS:
        loc = page.locator(sel)
        if loc.count() > 0:
            return loc, sel
    return page.locator(CARD_SELECTORS[0]), CARD_SELECTORS[0]


def close_apply_layer(page):
    """우측 입사지원 레이어를 확실하게 닫아서 화면 가림 방지"""
    try:
        # 1. ESC 키 누르기
        page.keyboard.press("Escape")
        time.sleep(0.5)

        # 2. X 닫기 버튼 직접 클릭
        close_btns = page.locator(
            "#quick_apply_layer .btn_close, .iframe_layer .btn_close, button[title='닫기'], a:has-text('닫기')"
        )
        for i in range(close_btns.count()):
            btn = close_btns.nth(i)
            if btn.is_visible():
                btn.click(force=True)
                time.sleep(0.5)

        # 3. 레이어 element 강제 제거 (JS)
        page.evaluate("""
            () => {
                const layers = document.querySelectorAll('#quick_apply_layer, .iframe_layer, #iframe_layer, #quick_apply_frame');
                layers.forEach(el => el.remove());
            }
        """)
        time.sleep(0.5)
    except Exception:
        pass


def find_apply_button_in_card(card):
    """목록 카드 내부의 작은 지원 버튼 탐색"""
    candidates = [
        card.locator("a.sri_btn_immediately, button.sri_btn_immediately"),
        card.locator("a[class*='btn_apply'], button[class*='btn_apply']"),
        card.locator("a:has-text('입사지원'), button:has-text('입사지원')"),
        card.locator("a:has-text('즉시지원'), button:has-text('즉시지원')"),
    ]
    for loc in candidates:
        try:
            for k in range(loc.count()):
                el = loc.nth(k)
                if el.is_visible():
                    txt = (el.inner_text() or "").strip()
                    if ("지원" in txt or "입사" in txt) and not any(kw in txt for kw in ["완료", "마감", "보기"]):
                        return el
        except Exception:
            continue
    return None


def click_red_layer_submit_button(context):
    """
    우측 레이어(또는 iframe) 내부의 배경이 빨간색/주황색인 진짜 제출 버튼만 정확하게 타깃팅
    """
    targets = []
    for pg in context.pages:
        targets.append(pg)
        for fr in pg.frames:
            targets.append(fr)

    submit_selectors = [
        "#quick_apply_layer button.btn_apply",
        "#quick_apply_layer .btn_apply",
        "div[class*='quick_apply'] button:has-text('입사지원')",
        "div[class*='layer'] button:has-text('입사지원')",
        "button[class*='btn_apply']",
        "#btn_apply",
        "button:has-text('입사지원')",
    ]

    for target in targets:
        for sel in submit_selectors:
            try:
                btn_loc = target.locator(sel)
                cnt = btn_loc.count()
                for i in range(cnt):
                    btn = btn_loc.nth(i)
                    if btn.is_visible():
                        box = btn.bounding_box()
                        # 화면 오른쪽 레이어 영역에 있는 지 확인 (X 좌표가 400px 이상)
                        if box and box["x"] > 400 and box["width"] > 100:
                            txt = (btn.inner_text() or "").strip()
                            if "보기" not in txt and "입사지원" in txt:
                                if DRY_RUN:
                                    print(f"  🎉 [드라이런] 우측 레이어 빨간색 제출 버튼 발견! (txt: '{txt}')")
                                    return True
                                else:
                                    btn.click(force=True)
                                    print(f"  🚀 우측 레이어 빨간색 입사지원 버튼 클릭 성공! (txt: '{txt}')")
                                    return True
            except Exception:
                continue

    return False


def run_agent():
    if not os.path.exists(STATE_PATH):
        print("⚠️ state.json 파일이 없습니다. save_login.py를 먼저 실행해 주세요.")
        return

    with sync_playwright() as p:
        browser = p.chromium.launch(
            channel="chrome",
            headless=False,
            args=["--disable-blink-features=AutomationControlled", "--start-maximized"],
        )
        context = browser.new_context(storage_state=STATE_PATH, no_viewport=True)
        page = context.new_page()

        search_url = (
            "https://www.saramin.co.kr/zf_user/search/recruit"
            f"?searchword={quote(SEARCH_KEYWORD)}"
        )
        print(f"[{SEARCH_KEYWORD}] 사람인 채용 검색 페이지로 이동 중...")
        page.goto(search_url)
        time.sleep(2)

        login_btn = page.query_selector("a:has-text('로그인')")
        if login_btn and login_btn.is_visible():
            print("⚠️ 세션이 만료되었습니다. 로그인을 다시 진행해 주세요.")
            input("엔터 키를 누르면 종료합니다...")
            browser.close()
            return
        print("✅ 로그인 상태가 정상 확인되었습니다.")

        print("⏳ 채용 공고 목록 스캔 중...")
        try:
            page.wait_for_selector(",".join(CARD_SELECTORS), state="attached", timeout=10000)
        except Exception:
            pass

        cards, card_sel = get_cards(page)
        card_count = cards.count()
        print(f"🔍 총 {card_count}개 카드 스캔 완료.")

        mode = "드라이런(테스트 모드)" if DRY_RUN else "🔥 실제 입사지원 모드"
        print(f"▶ 현재 모드: {mode} (최대 {MAX_APPLY}개 실행)\n")

        processed = 0

        for i in range(card_count):
            if processed >= MAX_APPLY:
                print(f"🎯 설정 수량({MAX_APPLY}개) 완료로 종료합니다.")
                break

            try:
                # 잔여 레이어 팝업 깔끔히 정리
                close_apply_layer(page)

                current_cards, _ = get_cards(page)
                if i >= current_cards.count():
                    break

                card = current_cards.nth(i)

                try:
                    card.evaluate("el => el.scrollIntoView({block: 'center'})")
                    time.sleep(0.3)
                except Exception:
                    continue

                list_btn = find_apply_button_in_card(card)
                if not list_btn:
                    continue

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
                time.sleep(random.uniform(1.5, 2.5))

            except Exception as e:
                print(f"  ⚠️ 오류 발생: {e}")
                close_apply_layer(page)
                continue

        print("\n✨ 모든 입사지원 작업이 완료되었습니다.")
        input("엔터 키를 누르면 브라우저를 종료합니다...")
        browser.close()


if __name__ == "__main__":
    run_agent()
```


1. agent.py 실행
2. 브라우저가 열리면 지정한 키워드 검색후 공고1번부터 N번까지 알아서 서류제출하는지 확인
3. 지원동기등 작성이 필요로하는 공고는 try-except 예외처리되서 자동 skip 되고 debug 에 남으므로, 가서 다시 작성가능