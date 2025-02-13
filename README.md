# API 명세-회원관리
| 기능 | Method | URL | response | 상태코드 |
| :-: | :-: | :-: | :-: | :-: |
| 회원가입 | POST | [http://localhost:8080/newSchedule?name={name}&password={password}&content={content}] (http://localhost:8080/member/new-member)| 등록 완료 여부 | 200: 정상 조회 |
| 날짜, 이름을 이용한 검색 | GET | http://localhost:8080/search/{name}/{시작날짜}/{끝날짜}| 검색된 일정 데이터 | 200: 정상 조회 |
| id를 이용한 검색 | GET | http://localhost:8080/search/{id} | 검색된 일정 데이터 | 200: 정상 조회 |
| 일정 수정 | POST | http://localhost:8080/edit?id={id}&pw={password}&name={name}&content={할일} | 수정 완료된 일정 데이터 | 200: 정상 조회 |
| 일정 삭제 | DELETE | http://localhost:8080/delete?id={id}&pw={password} | 삭제 완료 여부 | 200: 정상 조회 |
