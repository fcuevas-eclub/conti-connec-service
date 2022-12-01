# Step 1: run tests and build über jar
FROM maven:3.8-eclipse-temurin-11-alpine as build

# declarar variables de entorno
ARG github_actor
ARG github_token
ENV GITHUB_ACTOR=$github_actor
ENV GITHUB_TOKEN=$github_token

WORKDIR /build

# Cache Maven dependencies
COPY ./pom.xml .
COPY ./settings.xml .
RUN mvn -s settings.xml dependency:go-offline

# Build project
COPY ./src/ ./src/
RUN mvn package -DskipTests -s settings.xml


# Step 2: package über jar
FROM eclipse-temurin:11-jre-alpine
LABEL maintainer="fcuevas@eclub.com.py"

# Create system user
RUN apk add --no-cache alpine-conf && \
    setup-timezone -z America/Asuncion

RUN addgroup --system spring --gid 1000
RUN adduser --system spring --uid 1000 --ingroup spring
USER spring:spring

WORKDIR /home/spring
RUN mkdir pendientes
RUN mkdir procesados
RUN mkdir certificados
COPY --from=build --chown=spring:spring build/target/*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]