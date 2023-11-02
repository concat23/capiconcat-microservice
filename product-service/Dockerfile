# ============== VERSION 1 ===============
#FROM maven:3.8.2-jdk-8
#
#WORKDIR /ecommerce
#COPY . .
#RUN mvn clean install
#
#CMD mvn spring-boot:run
# ============= VERSION 2 ================
FROM maven:3.8.2 AS build
WORKDIR /product-service
COPY pom.xml /product-service
RUN mvn dependency:resolve
COPY .. /product-service
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:8-jdk-oracle
COPY --from=build /product-service/target/*.jar product-service.jar
EXPOSE 8080
CMD ["java","-jar","product-service.jar"]