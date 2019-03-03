FROM openjdk:8-jdk-alpine as builder
RUN apk add --no-cache maven
WORKDIR /app
COPY . .
RUN mvn clean package 
RUN ls /app/target


FROM openjdk:8-jdk-alpine as release
WORKDIR /app
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
CMD java -jar /app/demo-0.0.1-SNAPSHOT.jar
