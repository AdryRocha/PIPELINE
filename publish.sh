#!/bin/bash

docker login -u $DOCKER_REGISTRY_USERNAME -p $DOCKER_REGISTRY_PASSWORD $DOCKER_REGISTRY_URL
docker push $DOCKER_REGISTRY_URL/$DOCKER_REGISTRY_USERNAME/demo