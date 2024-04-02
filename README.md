서비스 시나리오 

# 서비스 시나리오

기능적 요구사항
1. 접수자가 접수한다.
2. 접수가 완료되면 의사에게 환자 정보가 등록된다 
3. 등록된 정보로 의사가 검사를 처방한다
4. 검사 정보가 생성된다 
5. 검사자가 검사를 시행 및 취소한다 
6. 의사가 확인한다.
7. 의사가 진료 종료/ 거부를 한다 
8. 접수자가 진료 완료,취소를 확인한다

비기능적 요구사항
1. 장애격리
    1. 진료 기능이 수행되지 않더라도 진료를 365일 병원 업무 시간 내에는 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
    2. 검사 기능이 수행되지 않더라도 검사를 365일 병원 업무 시간 내에는 받을 수 있어야 한다   Async (event-driven), Eventual Consistency
2. 성능
    1. 고객 관리팀은 현재 고객의 진료 상태를 확인할 수 있어야 한다.   CQRS


클라우드 아키텍쳐 설계 

# 클라우드 아키텍쳐 설계
![image](https://github.com/pyodol2/capstonproject/assets/145510412/7d7ae24d-7515-4c9c-ab9a-d0ede1035422)


클라우드 네이티브 모델링

# 클라우드 네이티브 모델링
![image](https://github.com/pyodol2/capstonproject/assets/145510412/089914e6-e887-4f89-86a4-b967351962ff)


# 기능 확인 


1. 접수자가 접수한다.

![image](https://github.com/pyodol2/capstonproject/assets/145510412/0089658a-cad0-4718-b23e-96583866b3b0)

접수된 데이터 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/79b1c971-b933-4881-b3a5-cfb54ffde040)

2. 접수가 완료되면 의사에게 환자 정보가 등록된다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/a135a719-4dd3-4f40-a613-898eed1712b8)


3. 등록된 정보로 의사가 검사를 처방한다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/c4ee1add-eb62-49e3-9579-e350f738f8c3)

4. 검사 정보가 생성된다
   
![image](https://github.com/pyodol2/capstonproject/assets/145510412/a0dd9fef-61ac-469e-b16a-00e6891d16dd)

   
5. 검사자가 검사를 시행 및 취소한다 

![image](https://github.com/pyodol2/capstonproject/assets/145510412/5d19c90c-f51b-41fe-bda5-03a9b41c1d50)


6. 의사가 확인한다.

![image](https://github.com/pyodol2/capstonproject/assets/145510412/660d925e-f28e-46b0-a426-976a9f2df0c3)

7. 의사가 진료 종료 및 거부를 한다

![image](https://github.com/pyodol2/capstonproject/assets/145510412/7c3523ca-e285-4059-830e-9275b594c52b)


9. 접수자가 진료 완료,취소를 확인한다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/9f65d345-5546-4fee-bf70-83d7cc56f8b7)

# 클라우드 네이티브 개발 

### Saga 
각 도메인에서 생성된 이벤트는 Kafka Topic 으로 들어간 후 메세지를 구독하여 이벤트로 처리하는 방식으로 진행.

### Compesnation
검사거부, 완료   진료 거부 ,완료시 자동으로 Status 변경 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/89e0a4c4-2c95-4f51-8c1b-a0c16288c7af)

###  GateWay 
모든 API는 게이트웨이를 통한 단일 진입점으로 서비스에 접근된다 
```
http http://localhost:8088/receptions/1 
http http://localhost:8088/diagnoses/1
http http://localhost:8088/examinations/1
```

### CQRS
다른 도메인의 서비스의 데이터 원본에 접근없이 내 서비스에서의 데이터 조회가 가능하다.

![image](https://github.com/pyodol2/capstonproject/assets/145510412/1f2e983f-4145-4490-a3fb-3e9334d7d059)


![image](https://github.com/pyodol2/capstonproject/assets/145510412/03c98565-e82d-45b5-9a42-aae3ac5d6af8)

