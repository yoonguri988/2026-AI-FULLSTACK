# 📘  RestClient와 Apache PDFBox를 활용한 파일 기반 RAG 생성형 AI 서비스 개발

---

## 📑 Part 1. 생성형 AI 핵심 이론: RAG (검색 증강 생성)

### 1. RAG(Retrieval-Augmented Generation)의 개념

RAG는 "AI에게 외부 지식을 검색할 수 있는 참고서(오픈북)를 쥐여주는 기술"입니다.

일반적인 LLM(ChatGPT 등)은 사전 학습된 데이터 내부에서만 답변하므로, 사내 문서나 실시간 최신 정보에 답할 수 없으며 거짓말을 지어내는 환각 현상(Hallucination)이 발생합니다. RAG는 이러한 단점을 보완하기 위해 "사용자의 질문에 부합하는 문서 내용을 먼저 찾은 뒤(Retrieval), 그 내용을 프롬프트에 결합하여(Augmentation), AI가 답변을 생성(Generation)하도록 유도"하는 기법입니다.

### 2. RAG의 핵심 장점

* **환각(Hallucination) 방지:** 주어진 문서 안에서만 근거를 찾아 답변하므로 무분별한 뇌피셜(거짓 답변)을 막습니다.
* **최신 정보 및 사내 데이터 활용:** 모델을 매번 새로 재학습(Fine-tuning)시키지 않고도, 실시간 업로드된 텍스트나 PDF 내용을 답변에 즉시 반영할 수 있습니다.
* **비용 최적화 및 보안:** 거대한 인프라 구축이나 복잡한 파이썬 LLM 스택(LangChain 등) 없이, 자바 백엔드 생태계 내부에서 경량 아키텍처로 구현이 가능합니다.

### 3. RAG의 3단계 작동 매커니즘

```
[사용자 질문] ➔ [1. 검색 (Retrieval)] ➔ [2. 증강 (Augmented)] ➔ [3. 생성 (Generation)]
                     └─ PDF 텍스트 추출         └─ 질문 + 문서 결합         └─ OpenAI API 호출

```
