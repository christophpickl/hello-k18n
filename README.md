Sample project to play around with kubernetes.

# TODO

## Roadmap

* [x] setup basic ktor services
* [x] deploy docker images to docker hub
* [x] run sample project manually in kubernetes
* [ ] run services via k8s manifest file
* [ ] add service inter-communication

## Major

* [ ] commons lib for ktor (router discovery)
* [ ] introduce arrow

## k8s features:

* [ ] helm charts (package management)
* [ ] prometheus (monitoring)
* [ ] store configuration in encrypted files
* [ ] fluentd (logging)
* [ ] istio (caching)
* [ ] load balancing
* [ ] facade with nginx
* [ ] security, auth; centralized
* [ ] persistence (exposed, volume mapping)

## Minor

* [ ] introduce kotlin script
* [ ] share build/plugin configuration via buildSrc
* [ ] introduce kodein
* [ ] introduce testng integration tests with wiremock

# Commands Help

* Build and run single docker image:
  1. Build JAR: `./gradlew users-service:service-impl:shadowJar`
  1. Build docker image: `docker build -t users-service:latest users-service/service-impl`
  1. Run service: `docker run -p 8081:80 users-service:latest`
  1. Request it: `curl http://localhost:8081`
* run docker:
  1. start local nodes: `minikube start`
  1. verify minikube is running: `docker ps`
  1. run service descriptor: `kubectl apply -f k8s-manifest.yaml`
  1. verify pod is up and running: `kubectl get pods`
  1. get details of pod: `kubectl describe pod users-service-pod`
  1. set proxy: `kubectl port-forward users-service-pod 8080:80` (blocks current terminal)
  1. request: `curl http://127.0.0.1:8080`