FROM maven:latest as maven-build
WORKDIR cinemaApp
COPY . .
RUN mvn clean package -DskipTests
FROM jboss/wildfly:latest as wildfly-server
COPY --from=maven-build /cinemaApp/target/qa-cinemas4.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
