# Steeltoe InitializrConfigServer

A [Spring Cloud Config Server](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html) for [Steeltoe InitializrApi](https://github.com/SteeltoeOSS/InitializrApi)

## Building and Running

### Using Gradle

```
$ ./gradlew bootRun
```

### Using Application Jar

```
$ ./gradlew bootJar
$ java -jar build/libs/Steeltoe.InitializrConfigServer-*.jar

```

## Options

### Git Backend
```
--spring.cloud.config.server.git.uri=https://my.git/user/repo
```

### Local Backend
```
--spring.profiles.active=native \
  --spring.cloud.config.server.native.searchLocations=file:///path/to/mybackend/
```


## Deploying

### Cloud Foundry

```
$ cf push -f deploy/cloud-foundry/manifest.yaml
```

## Client Examples

```sh
# get the development profile
$ http http://localhost:8888/SteeltoeInitializr/Development
# get the development profile per a brnach
$ http http://localhost:8888/SteeltoeInitializr/Development/master
# get the development profile per a tag
$ http http://localhost:8888/SteeltoeInitializr/Development/0.1.0
# get the development profile per a commit
$ http http://localhost:8888/SteeltoeInitializr/Development/f425c275a3bff305b0b3bf2b1c4586fa7400b527
```
