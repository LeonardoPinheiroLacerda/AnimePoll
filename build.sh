docker-compose down
docker rmi animepoll:v1.0.2
./mvnw spring-boot:build-image -DskipTests
docker-compose up -d