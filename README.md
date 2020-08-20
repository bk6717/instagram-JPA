# 스프링부트 JPA, MySQL, Security 인스타그램

의존성
- Spring Boot DevTools
- Lombok
- MySQL Driver
- Spring Security
- OAuth2 Client
- Mustache
- Spring Web

MySQL 데이터베이스 생성 및 권한주기 
- create user 'insta'@'%' identified by 'bitc5600';
- GRANT ALL PRIVILEGES ON 별.별 TO 'insta'@'%';
- create database insta;
- use insta;

##application.yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true
      
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/insta?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
    username: insta
    password: bitc5600
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      use-new-id-generator-mappings: false
    show-sql: true

  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB  #파일업로드 2메가로 제한

  security:
    user:
      name: cos
      password: 1234

         
# 파일 저장경로 설정 
file:
  path: C:/src/springwork/instagram/src/main/resources/upload 