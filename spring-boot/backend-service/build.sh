#!/bin/bash

echo "@.@"
echo "@.@ Build Application..." 
docker rmi -f dreamsalmon/boot-ehcache:latest
docker build -f Dockerfile -t dreamsalmon/boot-ehcache .
docker push dreamsalmon/boot-ehcache