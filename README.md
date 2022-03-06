# JanghowonTerminal

## 장호원시외버스터미널 시간표 만들기

프로젝트 개요
- 장호원의 공식 주소는 경기도 이천시 장호원읍으로 읍 단위의 작은 지역이다.
- 장호원은 주소상 경기도에 속하지만 충북과 맞닿을 정도로 외곽에 위치하고 있으며 다른 수도권에 비해서 교통 인프라가 매우 부족하다.
- 특히 터미널의 경우 제대로 된 시설이 갖추어지지 않은 상태로 컨테이너 형식의 시설을 유지하고 있는 상태이다.
- 다음 카페에 터미널 시간표가 있기는 하지만 별도의 웹이나 앱 서비스는 존재하지 않는다.
- 이번 프로젝트는 장호원 터미널을 이용하는 사람들이 사용할 수 있는 터미널 시간표 서비스를 구축하는 것을 목표로 한다.

요구사항
- 목적지를 선택하면 그에 맞는 교통편을 알 수 있다.
- 공지사항을 통해서 터미널과 관련된 소식을 알 수 있다.
- 관리자는 시간표를 추가, 수정, 삭제 등을 할 수 있는 권한을 가지고 있다.

기술 스택
- Spring Boot
- JPA(hibernate)
- Spring Security
- Mysql
- Thymeleaf 뷰 템플릿을 활용하여 Controller와 View단을 연결

## 과정

2022. 3. 5.
 - 공지사항 게시판을 먼저 구현하였다.
 - 페이징 기능의 경우 정확한 쿼리를 보내야하는데 그에 맞는 th:with를 통한 변수 생성과 로직을 구현하는 것이 쉽지 않았다.
 - Dto 객체 Entity를 자유자재로 변환할 수 있는 형태로 만드는 것이 중요하다는 것을 알게 되었다.
 - 실제 화면에서 보여지는 것은 Dto를 통해서 전달받기 때문이다.

2022. 3. 6.
 - 공지사항 게시판 및 시간표 조회 기능을 구현했다.
 - 게시판의 경우 모든 사람들이 게시글을 조회할 수 있도록 구현했다.
 - 하지만 게시글을 생성하고 수정, 삭제하는 기능은 관리자만 할 수 있도록 기능을 주는 것이 중요하다.
 
