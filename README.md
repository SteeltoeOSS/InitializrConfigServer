# Steeltoe InitializrConfigServer

A [Spring Cloud Config Server](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html) for [Steeltoe InitializrApi](https://github.com/SteeltoeOSS/InitializrApi)

## Build

```sh
./gradlew bootJar
```

## Docker

```sh
docker build -t initializr-config-server .
```

## Run

### Using Application Jar

```sh
java -jar build/libs/Steeltoe.InitializrConfigServer-*.jar <args>
```

### Using Gradle

```sh
./gradlew bootRun -Pargs=<args>
```

### Using Docker

```sh
docker run -it --rm -p 8888:8888 initializr-config-server <args>
```

## Options

### Logging

```text
logging.level.io.steeltoe={error,warn,info,debug,trace}
```

### Git Backend

```text
spring.cloud.config.server.git.uri=<url>
```

### Local Backend

```text
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations=file://<path>
```

## Sample URL paths

```text
# development profile
/SteeltoeInitializr/Development

# development profile per a branch
/SteeltoeInitializr/Development/master

# development profile per a tag
/SteeltoeInitializr/Development/0.1.0

# development profile per a commit
/SteeltoeInitializr/Development/f425c275a3bff305b0b3bf2b1c4586fa7400b527
```
