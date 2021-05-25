Sample project to play around with kubernetes.

# TODO

* look into:
  * [ ] helm (package management)
  * [ ] prometheus (monitoring)
  * [ ] store configuration in encrypted files
  * [ ] fluentd (logging)
* [ ] load balancing
* [ ] facade with nginx
* [ ] security, auth
* [ ] persistence (exposed, volume mapping)

## Minor

* [ ] introduce kotlin script
* [ ] share build/plugin configuration via buildSrc
* [ ] introduce kodein
* [ ] introduce testng integration tests with wiremock
* [ ] commons lib for ktor (router discovery)
* [ ] introduce arrow

# Commands Help

* Build and run:
  1. Build JAR: `./gradlew users-service:service-impl:shadowJar`
  1. Build docker image: `docker build -t users-service:latest users-service/service-impl`
  1. Run service: `docker run -p 8081:80 users-service:latest`
  1. Request it: `curl http://localhost:8081`
