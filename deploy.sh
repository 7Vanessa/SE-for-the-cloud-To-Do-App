#!/bin/bash

# Start Docker
docker &

# Start Minikube
minikube start

# Pull docker images
docker pull sabrinam2/todoapp-frontendservice:latest
docker pull sabrinam2/todoapp-taskmanagerservice:latest
docker pull sabrinam2/todoapp-filestorageservice:latest

# Apply Kubernetes configurations
kubectl apply -f front/frontend-deployment.yaml
kubectl apply -f front/frontend-service.yaml

kubectl apply -f taskmanagerservice/taskmanagerservice-mysql-pod.yaml
kubectl apply -f taskmanagerservice/taskmanagerservice-service.yaml

kubectl apply -f file-storage-service/filestorageservice-mysql-pod.yaml
kubectl apply -f file-storage-service/filestorageservice-service.yaml

kubectl apply -f ingress.yaml