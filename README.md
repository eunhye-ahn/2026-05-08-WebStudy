# HTML & CSS 전체 정리

---

### 텍스트 구조화

**블록 & 단락**

`<h1>`~`<h6>` 제목, `<p>` 단락, `<br>` 줄바꿈

**의미 & 스타일**

`<strong>` / `<b>` 굵게, `<del>` 취소선, `<ins>` 밑줄, `<sup>` 위첨자, `<sub>` 아래첨자, `<pre>` 입력 포맷 유지

**특수문자**

`&nbsp;` 공백, `&lt;` <, `&gt;` >

---

### 하이퍼링크 `<a>`

- `href="#id"` — 페이지 내 특정 위치로 이동
- `target="_self"` — 현재 탭에서 열기
- `target="_blank"` — 새 탭에서 열기

---

### 목록

- `<ul>` + `<li>` — 순서 없는 목록
- `<ol>` + `<li>` — 순서 있는 목록
- `<dl>` + `<dt>` + `<dd>` — 제목-설명 쌍 목록

---

### 테이블

- `<thead>` / `<tbody>` / `<tfoot>` — 테이블 영역 구분
- `<tr>` — 가로 한 줄
- `<th>` — 제목 셀 (굵게, 가운데 정렬)
- `<td>` — 데이터 셀 (왼쪽 정렬)
- `colspan="2"` — 열 병합 (가로)
- `rowspan="2"` — 행 병합 (세로)

---

### 블록 vs 인라인

- **블록** (`<div>`, `<p>`, `<h1>`~`<h6>`) — 한 줄 전체 차지, `width`/`height` 자유
- **인라인** (`<span>`, `<a>`, `<img>`) — 콘텐츠 크기만큼, `width`/`height` 적용 안 됨
- **인라인블록** (`display: inline-block`) — 나란히 배치 + 크기 조절 가능

---

### 선택자

- `A B` — 하위 선택자
- `A > B` — 자식 선택자
- `A + B` — 인접 형제
- `A ~ B` — 일반 형제

**속성 선택자**

- `[속성=값]` — 정확히 일치
- `[속성~=값]` — 단어 포함
- `[속성|=값]` — 값 일치 또는 하이픈으로 시작
- `[속성^=값]` — 해당 값으로 시작
- `[속성$=값]` — 해당 값으로 끝남
- `[속성*=값]` — 어디든 포함

---

### 가상 클래스 & 가상 요소

**링크 상태 순서**

`:link` → `:visited` → `:hover` → `:active`

**구조 선택자**

- `:first-child` — 첫 번째 자식
- `:nth-child(n)` — n번째 자식
- `:last-child` — 마지막 자식

**가상 요소**

- `::before` — 요소 앞에 콘텐츠 생성
- `::after` — 요소 뒤에 콘텐츠 생성

---

### 박스 속성

**margin** — 외부 간격

```css
margin: 10px;                 /* 상하좌우 동일 */
margin: 10px 20px;            /* 상하 | 좌우 */
margin: 10px 20px 30px;       /* 상 | 좌우 | 하 */
margin: 10px 20px 30px 40px;  /* 상 우 하 좌 */
margin: 0 auto;               /* 가운데 정렬 */
```

**padding** — 내부 간격 (margin과 단축 표기 동일)

**border** — 테두리

```css
border: 두께 스타일 색상;
/* 스타일: solid / dotted / dashed / double */
border-radius: 10px;
```

---

### 배경 속성

```css
background-color: red / #CCCCCC / rgb() / rgba();
background-image: url('경로');
background-repeat: repeat / no-repeat / repeat-x / repeat-y;
background-size: cover / contain / 100px;
background-position: center / top left;
background-attachment: fixed;
```

---

### 위치 속성

- `static` — 기본값, 문서 흐름대로
- `relative` — 원래 위치 기준 이동
- `absolute` — 부모 기준 절대 좌표
- `fixed` — 브라우저 화면 기준 고정

---

### 레이아웃 & 시맨틱 태그

- `<header>` — 로고, 검색
- `<nav>` — 메뉴
- `<aside>` — 사이드바, 광고
- `<section>` — 본문 콘텐츠
- `<footer>` — 개인정보처리방침, 회사정보

`flex` / `grid` — 현대적 레이아웃 배치

---

### 반응형 & 라이브러리

- `@media` — 해상도별 다른 CSS 적용
- **Bootstrap / Tailwind** — CSS 라이브러리 (Tailwind 많이 사용)
- 모바일 — React Native, Flutter
