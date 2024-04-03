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

![image](https://github.com/pyodol2/capstonproject/assets/145510412/d4ac20ec-8725-4cb2-95ee-ac9c030b5275)



6. 의사가 확인한다.

![image](https://github.com/pyodol2/capstonproject/assets/145510412/9b2199df-05a4-4e93-9ef6-bdee635b9f51)

   
7. 의사가 진료 종료/ 거부를 한다

![image](https://github.com/pyodol2/capstonproject/assets/145510412/e0766764-cb01-4e5b-897a-2bc625a29952)

8. 접수자가 진료 완료,취소를 확인한다

![image](https://github.com/pyodol2/capstonproject/assets/145510412/2bcf9da3-4c24-4ddd-ac2b-ebec7e8e724a)


### SAGA 
Choreography 방식
Kafka를 통해 게시-구독 원칙을 적용하여 각 마이크로 서비스는 자체 로컬 트랜잭션을 실행하고 이벤트를 메시지 브로커 시스템 에 게시하고 다른 마이크로 서비스에서 로컬 트랜잭션 을 트리거
### Compesnation
검사거부, 완료   진료 거부 ,완료시 자동으로 Status 변경 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/96606cbc-3b5c-4f31-84ba-922676201b35)


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

# 클라우드 배포 - Container 운영
AWS Code builder 를 통해 CI/CD 진행 

### 코드빌더 셋팅 


![image](https://github.com/pyodol2/capstonproject/assets/145510412/d7e3ba68-83af-45cc-bc22-3108e94bb1d0)


### ECR 셋팅 

![image](https://github.com/pyodol2/capstonproject/assets/145510412/6a1cff04-dec8-4ba5-9e0a-63899185e456)


### CI/CD 완료   

코드빌더의 WebHook의 buildspec-kubectl.yml  이용한 gateway   CI/CD 설정
```
version: 0.2

env:
  variables:
    _PROJECT_NAME: "user13-ecr"

    
     
phases:
  install:
    runtime-versions:
      java: corretto17
      docker: 20
    commands:
      - echo install kubectl
      - curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
      - chmod +x ./kubectl
      - mv ./kubectl /usr/local/bin/kubectl
  pre_build:
    commands:
      - cd gateway
      - echo Logging in to Amazon ECR...
      - echo $_PROJECT_NAME
      - echo $AWS_ACCOUNT_ID
      - echo $AWS_DEFAULT_REGION
      - echo $CODEBUILD_RESOLVED_SOURCE_VERSION
      - echo start command
      - $(aws ecr get-login --no-include-email --region $AWS_DEFAULT_REGION)
  build:
    commands:
      - echo Build started on `date`
      - echo Building the Docker image...
      - mvn package -Dmaven.test.skip=true
      - docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$_PROJECT_NAME:$CODEBUILD_RESOLVED_SOURCE_VERSION  .
  post_build:
    commands:
      - echo Pushing the Docker image...
      - docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$_PROJECT_NAME:$CODEBUILD_RESOLVED_SOURCE_VERSION
      - echo connect kubectl
      - kubectl config set-cluster k8s --server="$KUBE_URL" --insecure-skip-tls-verify=true
      - kubectl config set-credentials admin --token="$KUBE_TOKEN"
      - kubectl config set-context default --cluster=k8s --user=admin
      - kubectl config use-context default
      - |
          cat <<EOF | kubectl apply -f -
          apiVersion: v1
          kind: Service
          metadata:
            name: $_PROJECT_NAME
            labels:
              app: $_PROJECT_NAME
          spec:
            ports:
              - port: 8080
                targetPort: 8080
            selector:
              app: $_PROJECT_NAME
          EOF
      - |
          cat  <<EOF | kubectl apply -f -
          apiVersion: apps/v1
          kind: Deployment
          metadata:
            name: $_PROJECT_NAME
            labels:
              app: $_PROJECT_NAME
          spec:
            replicas: 1
            selector:
              matchLabels:
                app: $_PROJECT_NAME
            template:
              metadata:
                labels:
                  app: $_PROJECT_NAME
              spec:
                containers:
                  - name: $_PROJECT_NAME
                    image: $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$_PROJECT_NAME:$CODEBUILD_RESOLVED_SOURCE_VERSION
                    ports:
                      - containerPort: 8080
                    readinessProbe:
                      httpGet:
                        path: /actuator/health
                        port: 8080
                      initialDelaySeconds: 10
                      timeoutSeconds: 2
                      periodSeconds: 5
                      failureThreshold: 10
                    livenessProbe:
                      httpGet:
                        path: /actuator/health
                        port: 8080
                      initialDelaySeconds: 120
                      timeoutSeconds: 2
                      periodSeconds: 5
                      failureThreshold: 5
          EOF

cache:
  paths:
    - '/root/.m2/**/*'
```

### 빌드완료 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/011b08ac-77f7-4e97-a983-5101be3ef040)


쿠버네티스 배포 완료 
![image](https://github.com/pyodol2/capstonproject/assets/145510412/763aa7ce-d5e1-423d-8eec-12acdb6b015e)



# 컨테이너 인프라 설계 및 구성 역량

### 컨테이너 자동확장 - HPA 


```
# 오토 스케일링 설정명령어 호출
kubectl autoscale deployment user13-ecr --cpu-percent=20 --min=1 --max=3


# buildspec-kubectl.yml 해당 설정을 추가하고 Push 해서 배포한다.
 containers:
                  - name: $_PROJECT_NAME
                    image: $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$_PROJECT_NAME:$CODEBUILD_RESOLVED_SOURCE_VERSION
                    ports:
                      - containerPort: 8080
                    resources:
                      requests:
                        cpu: "200m"    

```
Siege를 통해 Auto Scale-Ou 증명한다 

![image](https://github.com/pyodol2/capstonproject/assets/145510412/a0ef3dd5-102c-40e1-9ec4-c8184d55c32a)



### 컨테이너로부터 환경분리 - CofigMap

ConfigMap을 쿠버네티스에 배포한다 
```
kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  ORDER_DB_URL: jdbc:mysql://mysql:3306/connectdb1?serverTimezone=Asia/Seoul&useSSL=false
  ORDER_DB_USER: myuser
  ORDER_DB_PASS: mypass
  ORDER_LOG_LEVEL: DEBUG
EOF
```


buildspec-kubectl.yml 해당 설정을 추가하고 Push 해서 배포한다.
```
                  env:
                      - name: ORDER_LOG_LEVEL
                        valueFrom:
                          configMapKeyRef:
                             name: config-dev
                             key: ORDER_LOG_LEVEL    


```

명령어를 통해서 Pod의 환경변수를 확인한다 

```
kubectl exec pod/user13-ecr-75d9765455-449q5 -- env
```
DEBUG로 설정되어있다.

![image](https://github.com/pyodol2/capstonproject/assets/145510412/0f366971-7bd6-4a14-ba6f-1975a2f9976a)




