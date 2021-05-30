Sample project to play around with kubernetes.

# TODO

## Roadmap

* [x] setup basic ktor services
* [x] deploy docker images to docker hub
* [x] run sample project manually in kubernetes
* [x] run services via k8s manifest file
* [ ] service inter-communication
* [ ] persistence (exposed, volume mapping)

## k8s features:

* [ ] helm charts (package management; when configuration k8s gets messy)
* [ ] istio/nginx (access from outside + caching)
* [ ] prometheus (monitoring)
* [ ] store configuration in encrypted files
* [ ] security, auth; centralized
* [ ] load balancing
* [ ] fluentd (logging)

## Minor

* [x] introduce kotlin script
* [x] share build/plugin configuration via buildSrc
* [x] introduce testng integration tests
* [ ] introduce kodein
* [ ] introduce integration tests with wiremock

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