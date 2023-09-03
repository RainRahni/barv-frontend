FROM maven:3.9.2-eclipse-temurin-17-alpine as builder

COPY ./barvBackEnd src/
COPY ./barvBackEnd pom.xml

RUN mvn -f pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
