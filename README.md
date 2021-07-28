# To Do List Web App

### 토이 프로젝트

참가자 : 권희창, 남윤한

### 기술 스텍

**Backend**

- SpringBoot + JPA
- MySQL
- AWS

**Frontend**

- React

### 기능 리스트

- User ID, Passward로 로그인 기능
- 유저별, 날짜별 ToDoList 한눈에 출력
- ToDoList 생성, 삭제 기능
- 완료한 항목 체크 기능
- 반복 일정 등록 기능

### 할일

- [ ] API 설계 및 DB 설계
- [ ] AWS, MySQL 연결
- [ ] 회원가입, 로그인 기능 구현

### DB 설계

UserTable

- Long index
- String userid
- String passward

ToDoTable

- Long id
- String title
- Long order
- Boolean completed
- Boolean repeated
- String date
- String userid

[API 명세](https://www.notion.so/787955d945a64e29a2f8f6f6658cb5b8)
