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
<span style="color:gray"> 2024.07~2024.06 | 1인 </span>

<p>&#160;</p>

## 📋설치 및 실행 방법
<details>
    <summary>사전 요구사항</summary>
    <ul>
        <li>Docker 및 Docker Compose 설치 </li>
        <li>Java 21 이상 설치</li>
        <li>Gradle 설치</li>
    </ul>
</details>
<details>
    <summary>클론 및 빌드</summary>

   ```
   git clone https://github.com/yourusername/binplay.git
   cd binplay
   ./gradlew build
   ```
</details>

<details>
    <summary>Docker를 사용한 애플리케이션 실행</summary>

   ```
   docker-compose up -d
   ```
</details>

<p>&#160;</p>

## 🛠️트러블 슈팅
### 1. 멀티 스레드 속도 향상
### 2. 동시성 이슈 해결

<p>&#160;</p>

## 🎯주요 기능
### 회원가입
- 구글 소셜 로그인 연동을 통해 간편하게 회원가입 및 로그인이 가능합니다.
### 동영상 관리
- 동영상 등록·수정·삭제·조회를 통해 업로드한 동영상을 관리할 수 있습니다.
- 동영상 재생 시 5분 간격으로 랜덤 광고가 배정됩니다.
- 마지막 재생 시점부터 동영상을 이어서 시청할 수 있습니다.
### 통계 데이터 조회
- 업로드한 동영상 중, 1일, 1주, 1달 동안의 조회수가 높은 동영상 TOP5를 조회할 수 있습니다.
- 업로드한 동영상 중, 1일, 1주, 1달 동안의 재생 시간이 긴 동영상 TOP5를 조회할 수 있습니다.
### 정산 데이터 조회
- 1일, 1주, 1달 총 정산 금액을 조회할 수 있습니다.
- 동영상 별, 광고 별 정산 금액을 조회할 수 있습니다.

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

