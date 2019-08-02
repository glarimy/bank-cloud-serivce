FROM maven:3.5-jdk-8 AS build
COPY src /usr/glarimy/src
COPY pom.xml /usr/glarimy
RUN mvn -f /usr/glarimy/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://mysqldb:3306/glarimy?useSSL=false&allowPublicKeyRetrieval=true","-jar","/usr/glarimy/target/bank-service-1.0.0.jar"]