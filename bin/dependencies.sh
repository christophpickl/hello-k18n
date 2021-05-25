#!/bin/bash

./gradlew  :users-service:service-impl:dependencies > build/dependencies.txt
open build/dependencies.txt
