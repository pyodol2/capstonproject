서비스 시나리오 

# 서비스 시나리오

기능적 요구사항
1. 환자가 접수자에게 인적 정보와 진료과를 말한다 
2. 접수자가 접수한다.
3. 접수가 완료되면 의사에게 환자 정보가 등록된다 
4. 등록된 정보로 의사가 검사를 처방한다
5. 검사자가 검사를 시행한다 
6. 환자가 검사 거부시 검사를 취소한다
7. 의사가 진료한다
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

