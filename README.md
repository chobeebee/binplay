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
<span style="color:gray">2024.06~2024.07 | 1인</span>

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
   git clone https://github.com/chobeebee/binplay.git
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
### 1. Spring Batch : 병렬 처리로 5% 데이터 처리 속도 향상
- **문제 상황** <br>
119,200,000건 통계·정산 작업 실행 시, 데이터 처리 시간이 예상보다 지체되어 전체 배치 작업이 지연되는 상황 발생
- **해결 과정** <br>
동영상과 광고 별로 통계·정산을 그룹화하고 각 작업을 TaskExecutor를 사용하여 비동기적으로 실행한 후, CountDownLatch를 사용해 각 단계가 순차적으로 진행되도록 구현
- **결과** <br>
순차 처리 시간은 1224.057초 소요되고 병렬 처리 시간은 1163.192초 소요됨으로써 <u>**총 60.865초 단축 성공**</u>

### 2. 대량 데이터 삽입 및 최적화
- **문제 상황** <br>
   1,000,000건의 대용량 데이터 처리에 대한 Spring Batch 속도를 측정하기 위해
   Spring Boot Test를 이용하여 데이터를 삽입하는 과정에서 538분 소요 시간이 발생
- **해결 과정** <br>
  1) 프로시저를 작성하여 삽입 시도
      - 배치 처리를 통해 대량의 데이터를 효율적으로 삽입 → 처리 속도 61.97 분
  2) 서브쿼리와 랜덤 값을 이용한 MySQL 쿼리
      - 단일 SQL 쿼리 내에서 대량의 데이터를 생성하고 삽입 → 처리 속도 22.42분
- **결과** <br>
     기존 538분 소요 시간에서 515분을 단축하여 <u>**95.78% 성능 향상**</u> 달성

<p>&#160;</p>

## 🕹️상세 기능
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

