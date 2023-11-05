# BUILD STAGE
FROM maven:3.9.5-amazoncorretto-8 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean install

# PACKAGE STAGE
FROM amazoncorretto:8-alpine3.15
COPY --from=build /usr/app/target/service_soap-jar-with-dependencies.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar