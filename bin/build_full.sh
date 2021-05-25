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
    echo "Supported arg: { useres | books }"
    exit 1
    ;;
esac

echo "Building shadow jar ..."
./gradlew ${PROJECT_ID}:service-impl:shadowJar || exit

echo "Building docker image ..."
docker build -t ${PROJECT_ID}:latest ${PROJECT_ID}/service-impl || exit

echo "Building '${PROJECT_ID}' successful ✅"
