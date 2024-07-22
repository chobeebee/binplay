<p align="center">
  <img align="center" src="https://postfiles.pstatic.net/MjAyNDA3MThfNjEg/MDAxNzIxMjk4NTM0Mjcw.gCx4GFSYGo0iljD7LYFDFWxPBUUxrmu4alHpXQvYjL4g.CufZ4V291zUpmrjN2vVQBU4u-fMLnMGSmHdCRFQ4muAg.PNG/binplay_(1).png?type=w966">
</p>

<div align="center">

  ![Spring Boot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=for-the-badge&logo=springBoot&logoColor=white)
  ![Spring Batch](https://img.shields.io/badge/-SpringBatch-6DB33F?style=for-the-badge&logo=springBoot&logoColor=white)
  ![Spring Security](https://img.shields.io/badge/-SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white) <br>
  ![Java](https://img.shields.io/badge/-Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  ![Gradle](https://img.shields.io/badge/-Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
  ![MYSQL](https://img.shields.io/badge/-MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
  ![Docker](https://img.shields.io/badge/-Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
  ![Redis](https://img.shields.io/badge/-Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white)
    
</div>


<p>&#160;</p>

# 📌Binplay
### 스트리밍 서비스 정산 프로젝트
<p>유저에게 업로드한 동영상 별로 조회수에 따른 통계와 정산을 제공하는 프로젝트입니다.</p>

<p>&#160;</p>

## 🎯주요 기능
1. 회원가입
    - 소셜 로그인(구글)을 통해 간편한 회원가입 제공
    - 로그인 및 로그아웃
2. 동영상 관리
    - 동영상 등록·수정·삭제·조회
    - 5분 간격 랜덤 광고 배정
    - 최근 재생 시점부터 동영상 재생
3. 통계 데이터 조회
    - 1일, 1주, 1달 동안의 조회수가 높은 동영상 TOP5
    - 1일, 1주, 1달 동안의 재생 시간이 긴 동영상 TOP5
4. 정산 데이터 조회
    - 1일, 1주, 1달 총 정산 금액
    - 동영상 별, 광고 별 정산

<p>&#160;</p>


## ✏️ERD & API
<details> 
    <summary>ERD</summary>
    <img src="https://postfiles.pstatic.net/MjAyNDA3MjBfMTQw/MDAxNzIxNDA5ODk2MTM2.7ywqKUn2baDmlwFBNA6UWSsNlCRoWkBbRGDzuuY9mUYg.1uArCbuwAW4CP4oQdXtqH1loOHayqaoN0YHt7olEQuYg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-07-20_022435.png?type=w966" width="620">
</details>
<details> 
    <summary>API</summary>
    추가 예정
</details>

<p>&#160;</p>

## 📋실행 방법
### Docker를 사용한 설치 및 실행 방법
<details>
    <summary>1. 프로젝트 클론</summary>

   ```
   git clone https://github.com/chobeebee/binplay.git
   cd binplay
   ```
</details>

<details>
    <summary>2. docker-compose.yml 파일 작성</summary>

   ```
   version: '3.8'
   services:
      db:
        image: mysql:8.0
        environment:
          MYSQL_ROOT_PASSWORD: rootpassword
          MYSQL_DATABASE: mydatabase
          MYSQL_USER: user
          MYSQL_PASSWORD: password
        ports:
          - "3306:3306"
        volumes:
          - db-data:/var/lib/mysql
    
      app:
        build: .
        ports:
          - "8080:8080"
        depends_on:
          - db
    
   volumes:
      db-data:
   ```
</details>

<details>
    <summary>3. Docker 컨테이너 실행</summary>

   ```
   docker-compose up
   ```
</details>

<p>&#160;</p>

## 🛠️트러블 슈팅
### 1. 멀티 스레드 속도 향상
### 2. 동시성 이슈 해결

<p>&#160;</p>

