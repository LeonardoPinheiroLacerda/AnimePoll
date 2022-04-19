docker-compose down
docker rmi animepoll:v1.0.1
./mvnw spring-boot:build-image -DskipTests
docker-compose up -d