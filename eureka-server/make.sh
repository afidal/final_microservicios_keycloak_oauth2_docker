mvn clean && mvn package -DskipTests
docker build . -t eureka-server