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

# Binplay 소개
### 스트리밍 정산 프로젝트

- <b>대용량 데이터를 효율적으로 처리하는 것을 목표</b>로 진행한 프로젝트
- 유저에게 업로드한 동영상 별로 조회수에 따른 <b>통계와 정산</b>을 제공합니다.

> 프로젝트 기간 : 2024.06 ~ 2024.07 <br>
> 인력 구성(기여도) : BE 1명 (기여도 100%)

<p>&#160;</p>

## 목차
- [📋설치 및 실행 방법](#설치-및-실행-방법)
- [🛠️트러블 슈팅](#%EF%B8%8F트러블-슈팅)
    - Spring Batch 데이터 처리 속도 개선
    - 대량 데이터 삽입 및 최적화
- [✏️주요 기능](#%EF%B8%8F주요-기능)
- [🕹️프로젝트 상세](#%EF%B8%8F프로젝트-상세)
    - API문서
    - ERD

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
### 1. Spring Batch 데이터 처리 속도 개선
<a href="https://sulfuric-halibut-137.notion.site/SpringBatch-d356a80f42334c94b33461359d204455?pvs=4" alt="트러블슈팅 SpringBatch">🔗자세히 보기</a>

| 수정 적용        | 소요시간            | 개선율      |
|--------------|-----------------|----------|
| Tasklet      | 2분 8초 848밀리초    | -        |
| Chunk(순차)    | 18초 198밀리초      | 약 85.90% |
| Chunk(멀티스레드) | 16초 668밀리초      | 약 8.41%  |
| 최종 개선율       | - 1분 52초 180밀리초 | 약 8.41%  |

- **문제 상황** <br>
  45,000,000건 통계·정산 작업 실행 시, 데이터 처리 시간이 예상보다 지체되어 전체 배치 작업이 지연되는 상황 발생
- **해결 과정** <br>
  Tasklet 방식에서 Chunk 방식으로 변경 후, TaskExecutor를 사용하여 비동기적으로 실행시키며 Reader와 Writer를 Thread-Safe한 컴포넌트로 수정하여 멀티 스레드를 적용
- **결과** <br>
  Tasklet 처리 시간은 2m 8s 848m 소요되고, Chunk(멀티스레트) 처리 시간은 16s 668ms 소요되어 <u>**총 1m 52s 180ms로 단축 성공**</u>

### 2. 대량 데이터 삽입 및 최적화
<a href="https://sulfuric-halibut-137.notion.site/1-000-000-a6936d0db79742c9ab7c5cdbfa259dc1?pvs=4" alt="트러블슈팅 데이터삽입">🔗자세히 보기</a>

| 단계  | 내용          | 처리 속도 (분) | 개선율 (%) |
|-----|-------------|-----------|---------|
| 1단계 | SpringBootTest | 538분      | -       |
| 2단계 | 프로시저        | 61.97분    | 88.48%  |
| 3단계 | 서브쿼리        | 22.42분    | 63.82%  |

- **문제 상황** <br>
  1,000,000건의 대용량 데이터 처리에 대한 Spring Batch 속도를 측정하기 위해
  Spring Boot Test를 이용하여 데이터를 삽입하는 과정에서 538분 소요 시간이 발생
- **해결 과정** <br>
    1) 프로시저를 작성하여 삽입 시도
        - 배치 처리를 통해 대량의 데이터를 효율적으로 삽입 → 처리 속도 61.97 분
    2) 서브쿼리와 랜덤 값을 이용한 MySQL 쿼리
        - 단일 SQL 쿼리 내에서 대량의 데이터를 생성하고 삽입 → 처리 속도 22.42분
- **결과** <br>
  기존 538분 소요 시간에서 515분을 단축하여 <u>**95.83% 성능 향상**</u> 달성

<p>&#160;</p>

## 🕹️주요 기능

### 소셜 로그인(Google)

- OAuth2.0 클라이언트와 Spring Security6 프레임워크를 활용
- Google로 부터 인증을 받은 후 전달 받은 데이터를 활용하여 JWT 발급하고 인가 진행

### 통계·정산 자동화
- Spring Batch와 스케쥴러를 이용하여 특정 시간에 통계,정산 기능이 실행되도록 설정

### CRUD 기능 구현
- 동영상 등록·수정·삭제·조회
- 1일, 1주, 1달 동안 조회수가 높은 동영상 TOP5 조회
- 1일, 1주, 1달 동안 재생 시간이 긴 동영상 TOP5 조회
- 1일, 1주, 1달 정산 금액 조회

<p>&#160;</p>

## ✏️프로젝트 상세
#### API
[![POSTMAN](https://img.shields.io/badge/Postman-API_Link-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://documenter.getpostman.com/view/33433405/2sAXqmBQtQ)
#### ERD
<details> 
    <summary>ERD IMAGE</summary>
    <img src="https://postfiles.pstatic.net/MjAyNDA3MjBfMTQw/MDAxNzIxNDA5ODk2MTM2.7ywqKUn2baDmlwFBNA6UWSsNlCRoWkBbRGDzuuY9mUYg.1uArCbuwAW4CP4oQdXtqH1loOHayqaoN0YHt7olEQuYg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-07-20_022435.png?type=w966" width="620">
</details>

<p>&#160;</p>