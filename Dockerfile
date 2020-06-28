FROM bellsoft/liberica-openjdk-alpine:14 as build
WORKDIR /scratch
COPY . .
RUN ./gradlew --no-daemon build

FROM bellsoft/liberica-openjdk-alpine:14
WORKDIR /srv
COPY --from=build /scratch/build/libs/Steeltoe.Initializr.ConfigServer-*.jar Steeltoe.Initializr.ConfigServer.jar
EXPOSE 8888
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "Steeltoe.Initializr.ConfigServer.jar"]
