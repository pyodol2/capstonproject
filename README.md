서비스 시나리오 

# 서비스 시나리오

기능적 요구사항
1. 접수자가 접수한다.
2. 접수가 완료되면 의사에게 환자 정보가 등록된다 
3. 등록된 정보로 의사가 검사를 처방한다
4. 검사 정보가 생성된다 
5. 검사자가 검사를 시행한다 
6. 환자가 검사 거부시 검사를 취소한다
7. 의사가 진료완료한다.
8. 환자가 진료 거부시 진료를 취소한다
9. 접수자가 진료 완료,취소를 확인한다

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

gateway를 통해 접수한다 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/0089658a-cad0-4718-b23e-96583866b3b0)

접수된 데이터 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/79b1c971-b933-4881-b3a5-cfb54ffde040)



2. 접수가 완료되면 의사에게 환자 정보가 등록된다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/a135a719-4dd3-4f40-a613-898eed1712b8)


3. 등록된 정보로 의사가 검사를 처방한다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/c4ee1add-eb62-49e3-9579-e350f738f8c3)

4. 검사 정보가 생성된다
![image](https://github.com/pyodol2/capstonproject/assets/145510412/a0dd9fef-61ac-469e-b16a-00e6891d16dd)

   
6. 검사자가 검사를 시행한다 

7. 환자가 검사 거부시 검사를 취소한다

8. 의사가 진료완료한다.

9. 환자가 진료 거부시 진료를 취소한다

10. 접수자가 진료 완료,취소를 확인한다
