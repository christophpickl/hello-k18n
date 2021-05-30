#!/bin/bash

PROJECT=$1
PROJECT_ID=""
case $PROJECT in
  users)
    PROJECT_ID="users-service"
    ;;
  books)
    PROJECT_ID="books-service"
    ;;
  *)
    echo "Supported arg: { users | books }"
    exit 1
    ;;
esac

echo "Building shadow jar ..."
./gradlew ${PROJECT_ID}:service-impl:shadowJar || exit

echo "Building docker image ..."
docker build -t christophpickl/${PROJECT_ID}:latest ${PROJECT_ID}/service-impl || exit
echo "Pushing docker image ..."
docker push christophpickl/${PROJECT_ID}:latest

echo "Building '${PROJECT_ID}' successful ✅"

# dry run: kubectl run drypod --image christophpickl/users-service:latest
# proxy: kubectl port-forward drypod 8080:80
# request: curl --head http://127.0.0.1:8080 (executed in a new terminal, then kill port-forward process)
# check custom logs: kubectl logs drypod