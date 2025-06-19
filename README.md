# Memoir Backend (SpringBoot)

이 레포지토리는 이화여자대학교 2025-1학기 캡스톤디자인 ‘그로쓰’ 수업의 28팀 HUK 프로젝트인 memoir의 backend 부분을 다루고 있습니다.

---
1. 개발환경 세팅
   
   - Spring Boot 3.x 이상은 JDK 17 이상이 필수입니다.
	    ```
	    gradle -v
	  	java -version
	    ```
	    JDK 17이 있는 지 확인 후 없다면 설치합니다.
	   
	   JDK 17 설치 방법 (맥북)
	   ```
	   brew install openjdk@17
	   echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
	   source ~/.zshrc
	   java -version
	   ```
    - IDE: IntelliJ IDEA
    - Gradle: wrapper 사용 (./gradlew)
    - DB: MySQL 8.x

2. GitHub 리포지토리 클론
   
    ```
    git clone https://github.com/capstone-huk/Backend.git
    cd Backend
    ```

	
3. `.env` 파일 설정
   
   /Backend/.env 에 `.env`파일 생성
4. `.yml`파일 설정

   - 로컬 개발 시 `.env`에 `SPRING_PROFILES_ACTIVE=dev` 설정
   - 배포 시 `.env에 `prod 설정
   
6. 데이터베이스 연결
   
   `.env`에 있는 정보를 바탕으로 데이터베이스를 연결합니다.
7. 의존성 설치 및 빌드
   ```
	./gradlew clean build
   ```
9. 로컬 서버 실행
    ```
   ./gradlew bootRun
    ```
