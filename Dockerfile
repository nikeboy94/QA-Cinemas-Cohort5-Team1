FROM maven:latest as maven-build
WORKDIR /var/jenkins_home/workspace/docker_cinema/
RUN ls
RUN mvn clean install -DskipTests
FROM jboss/wildfly:latest as wildfly-server
COPY --from=maven-build /var/jenkins_home/workspace/docker_cinema/ /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
