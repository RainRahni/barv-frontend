FROM maven:3.9.2-eclipse-temurin-17-alpine as builder

COPY ./barvBackEnd src/
COPY ./barvBackEnd pom.xml

RUN mvn -f pom.xml package -DskipTests

- name: Print contents of /tmp/buildkit-mount300792122/
  run: |
    ls -la /tmp/buildkit-mount300792122/

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
