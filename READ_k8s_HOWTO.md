# Kubernetes Howto

## Setup Infrastructure

* Install kubernetes: `brew install kubectl`
    * Verify: `kubectl version --client`
* Install minikube: `brew install minikube`
    * Verify: `minikube version`
    * Run: `minikube start` (`--driver=docker --memory=3000`)
    * Verify kubernetes server: `kubectl version`
* Look around:
    * `kubectl cluster-info`
    * `kubectl get nodes` (should show minikube)

## Setup local docker registry (for k8s)

* NO: would need secret, etc... better just use hub.docker.com ;)
* ~~reason: k8s seems not to be able to look into the local docker image repository, so we need to deploy it
  somewhere...~~
* ~~run registry in a container:~~ `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
* ~~tag image to local registry~~ `docker tag users-service:latest <HOST_IP>:5000/users-service:latest`
* ~~push to registry:~~ `docker push <HOST_IP>:5000/users-service:latest`
* ~~CAVE! kubernetes will complain regarding security (HTTPS)~~
    * ~~open Docker for Mac preferences / Docker Engine, and add:~~ `"insecure-registries":["<HOST_IP>:5000"]` (this is
      analogous editing the `/etc/docker/daemon.json` file)
    * ~~restart docker daemon~~
    * ~~verify:~~ `docker info` (should list 'Insecure Registries' properly)
* ~~k8s should now be able to find it~~ (specifing the image with `image: <HOST_IP>:5000/users-service:latest`)
* ~~dry run:~~ `kubectl run test --image <HOST_IP>:5000/users-service:latest`
* ~~ADDITIONALLY: start minikube with this config:~~ `minikube start --insecure-registry=<HOST_IP>:5000`

* login: `docker login` using your docker ID (not email address) and your set password
* tag local image to dockerhub: `docker tag users-service:latest christophpickl/users-service:latest`
* push image: `docker push christophpickl/users-service:latest`
* dry run: `kubectl run drypod --image christophpickl/users-service:latest`
* proxy: `kubectl port-forward drypod 8080:80`
* request: `curl --head http://127.0.0.1:8080` (executed in a new terminal, then kill port-forward process)
* check custom logs: `kubectl logs drypod`

## Setup Manually

* Deploy services:
    * Deploy: `kubectl create deployment users-service --image=users-service:latest`
        * FIXME !!! images can't be found (ImagePullBackOff)
        * troubleshooting:
            * `kubectl get pods`
            * `kubectl describe pod $POD`
    * ... deploy other services
    * Verify: `kubectl get deployments`
* Run proxy (access to internal k8s network): `kubectl proxy`
    * Verify service running: `curl http://localhost:80`
* Interact with the pod
    * Get the pod's name: `kubectl get pods` or `kubectl get pods -o wide`
        * Or extract and save
          it: `export POD=$(kubectl get pods -o go-template --template '{{range.items}}{{.metadata.name}}{{"\n"}}{{end}}')`
    * Request service in pod: `curl http://localhost:8001/api/v1/namespaces/default/pods/$POD/proxy/`
    * Get logs from pod: `kubectl logs $POD`
    * List ENV variables: `kubectl exec $POD -- env`
    * Open interactive shell: `kubectl exec -ti $POD -- bash`
    * Request on local pod: `curl http://localhost:80`

## Setup with manifest

* Start up (like docker compose): `kubectl apply -f k8s-manifest.yaml`
* Verify: `kubectl get pods`, Troubleshoot: `kubectl describe pod $POD`

## Nginx demo

1. `minikube start` (if not yet done so)
1. `kubectl create deployment nginx --image=nginx`
1. `kubectl get pods -l app=nginx` (status of pod should be 'Running')
1. `POD_NAME=$(kubectl get pods -l app=nginx -o jsonpath="{.items[0].metadata.name}")`
1. `kubectl port-forward $POD_NAME 8080:80`
1. `curl --head http://127.0.0.1:8080` (executed in a new terminal, then kill port-forward process)
1. `kubectl logs $POD_NAME`
1. `kubectl exec -ti $POD_NAME -- nginx -v`

## Cleanup

* List deployments: `kubectl get deployments`
* Scale down deployment: `kubectl scale --replicas=0 deployment/<DEPLOYMENT_NAME>`
* Delete them: `kubectl delete deployments <DEPLOYMENT_NAME>`
* List pods: `kubectl get pods`
* Delete pods: `kubectl delete pod $POD` or forcefully with `--grace-period=0`
