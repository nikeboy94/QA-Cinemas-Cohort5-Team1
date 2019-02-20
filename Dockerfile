FROM maven:latest as maven-build
WORKDIR /home/
RUN mvn clean install -DskipTests
FROM jboss/wildfly:latest as wildfly-server
COPY --from=maven-build <> /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
