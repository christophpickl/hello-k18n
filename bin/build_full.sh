#!/bin/bash

echo "Building shadow jar ..."
./gradlew users-service:service-impl:shadowJar || exit

echo "Building docker image ..."
docker build -t users-service:latest users-service/service-impl || exit

echo "Build successful ✅"
