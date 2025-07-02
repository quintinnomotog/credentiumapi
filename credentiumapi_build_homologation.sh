#!/bin/bash

nohup mvn clean install package -DskipTests > maven.log 2>&1 &

nohup java -jar target/credentiumapi-0.0.1-SNAPSHOT.jar -DskipTests > application.log 2>&1 &