# AWS Service Discovery

### Build
###### Build application and create local image
```
mvn clean package dockerfile:build -Ddocker.repository=<repository_name>
```

###### Build application and push image to remote repository
```
mvn clean package dockerfile:push -Ddocker.repository=<repository_name>
```

### Run
###### Local
Message Service
```
VM Options: -Dserver.port=8010 -Dmanagement.server.port=8011
```

Random Service
```
VM Options: -Dserver.port=8020 -Dmanagement.server.port=8021
```

Welcome Service
```
VM Options: -Dapp.feign.message.url=http://localhost:8010/api/v1/message -Dapp.feign.random.url=http://localhost:8020/api/v1/random
```

###### docker compose
```
docker-compose up -d
```

### Test
###### Local
Message Service
```
http://localhost:8010/api/v1/message/hostname
http://localhost:8011/actuator/health
```

Random Service
```
http://localhost:8020/api/v1/random/number
http://localhost:8021/actuator/health
```

Welcome Service
```
http://localhost:8000/api/v1/welcome/message
http://localhost:8001/actuator/health
```

### Deploy in AWS ECS
###### Create new VPC, 2 public subnet and 2 private subnet
```
aws cloudformation create-stack --stack-name TEST-VPC --template-body file://vpc.yml
```

###### Deploy application in ECS cluster
When message service container comes up, it goes and register in AWS service discovery and it can be accessed using 'messageservice.messagesvcnamespace' in the same VPC.
```
aws cloudformation create-stack --stack-name SERVICE-DISCOVERY-POC --template-body file://service.yml \
    --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM \
    --parameters ParameterKey=VpcId,ParameterValue=vpc-XXXXXXXXXXXX \
    ParameterKey=PublicSubnetList,ParameterValue='subnet-XXXXXXXXXXXX,subnet-XXXXXXXXXXXX' \
    ParameterKey=PrivateSubnetList,ParameterValue='subnet-XXXXXXXXXXXX,subnet-XXXXXXXXXXXX'
```

###### Invoke services using ALB
```
http://APP-DISCOVERY-ALB-XXXXXXXXX.us-east-1.elb.amazonaws.com/api/v1/welcome/message
http://APP-DISCOVERY-ALB-XXXXXXXXX.us-east-1.elb.amazonaws.com/api/v1/message/hostname
http://APP-DISCOVERY-ALB-XXXXXXXXX.us-east-1.elb.amazonaws.com/api/v1/random/number
http://APP-DISCOVERY-ALB-XXXXXXXXX.us-east-1.elb.amazonaws.com/api/v1/swagger-ui.html
```