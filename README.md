## 📌 회원 관리 API 명세서

| No. | API명 | Method | URL | 요청 데이터 (Form-Data) | 응답 예시 | 상태 코드 |
|----|------|--------|--------------------|----------------------------|-----------------------------|------------|
| 1  | 회원 가입 | `POST` | `/member/new-member` | `name` (text, ✅) <br> `email` (text, ✅) <br> `password` (text, ✅) | `16번 회원 생성 완료` | `200 OK` |
| 2  | 로그인 | `POST` | `/member/login` | `email` (text, ✅) <br> `password` (text, ✅) | `{"name": "user6","email": "user6@user6.com",{"password": "userpassword6"},{"createdDate": "2025-02-13T02:14:21.453445",{"modifiedDate": "2025-02-13T02:14:21.453445"}` | `200 OK` |
| 3  | 로그인 회원 검색 | `GET` | `/member/search` | 없음 | `{"name": "user6","email": "user6@user6.com","password": "userpassword6","createdDate": "2025-02-13T02:14:21.453445","modifiedDate": "2025-02-13T02:14:21.453445"}` | `200 OK` |
| 4  | 회원 수정 | `POST` | `/member/edit` | `email` (text, ❌) <br> `name` (text, ❌) <br> `password` (text, ❌) | `{"name": "수정6","email": "user6@user6.com","password": "userpassword6","createdDate": "2025-02-13T02:14:21.453445","modifiedDate": "2025-02-13T02:14:21.453445"}` | `200 OK` |
| 5  | 회원 삭제 | `DELETE` | `/member/delete` | 없음 | `회원 삭제 완료` | `200 OK` |
| 6  | 로그아웃 | `GET` | `/member/logout` | 없음 | `로그아웃하였습니다.` | `200 OK` |

❗ **프로젝트 루트경로에 postman_collection.json 형식의 API포함**<br>
✅ **필수 입력 필드**  
❌ **선택 입력 필드**

📌 **상태 코드 설명**
- `200 OK` : 정상 요청 성공  
- `400 Bad Request` : 잘못된 요청 (필수 값 누락 등)
- `401 Unauthorized` : 인증 실패


## 📌 일정 관리 API 명세서

| No. | API명 | Method | URL | 요청 데이터 (Form-Data) | 응답 예시 | 상태 코드 |
|----|------|--------|--------------------------|-----------------------------|-------------|------------|
| 1  | 일정 생성 | `POST` | `/schedule/new-schedule` | `title` (text, ✅) <br> `task` (text, ✅) | 17번 일정 생성 완료 | `200 OK` |
| 2  | 내 일정 검색 | `GET` | `/schedule/search` | `{"id":16,"title":"제목6",<"createdDate":"2025-02-13T02:15:45.909376","modifiedDate":"2025-02-13T02:18:14.357154","name":"user6"}` |  | `200 OK` |
| 3  | 내 일정 검색 (날짜) | `POST` | `/schedule/search/date` | `startDate` (text, ✅) <br> `endDate` (text, ✅) | `{"id":16,"title":"제목6","createdDate":"2025-02-13T02:15:45.909376","modifiedDate":"2025-02-13T02:18:14.357154","name":"user6"}` | `200 OK` |
| 4  | 일정 수정 | `POST` | `/schedule/edit/{id}` | `title` (text, ✅) <br> `task` (text, ✅) | `{"id":16,"title":"제목6","createdDate":"2025-02-13T02:15:45.909376","modifiedDate":"2025-02-13T02:18:14.357154","name":"user6"}` | `200 OK` |
| 5  | 일정 삭제 | `DELETE` | `/schedule/delete/{id}` | 없음 | `16번 일정을 삭제했습니다` | `200 OK` |

❗ **프로젝트 루트경로에 postman_collection.json 형식의 API포함**<br>
✅ **필수 입력 필드**  
❌ **선택 입력 필드**

📌 **상태 코드 설명**
- `200 OK` : 정상 요청 성공  
- `400 Bad Request` : 잘못된 요청 (필수 값 누락 등)
- `401 Unauthorized` : 인증 실패

## 📌 ERD
![image](https://github.com/user-attachments/assets/80eee075-e062-4161-bf63-ac5cf34b532c)
