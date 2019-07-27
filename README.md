# Project Description
This projects aims to accept movie name and push it to kafka
## Dev Environment Setup for OS X.
* Java 1.8.0_112 and above
```
$ brew cask install java
```

## Test instructions
```
$ ./gradlew clean test
```

## Build instructions
```
$ ./gradlew clean build
```

# How to Use
```
 1. docker-compose up -d
 2.  Use POST [http://127.0.0.1:8090/publish_movie] endpoint to publish movie names to kafka console producer.
 3. Import postman collection provided in project.
 2. Use kafka console consumer to test messages are producing successfully.
    kafka-console-consumer --bootstrap-server localhost:2181 --topic hello
```