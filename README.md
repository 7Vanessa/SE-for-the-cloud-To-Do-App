# SE-for-the-cloud-To-Do-App

# TodoApp

This repository contains the source code for a TodoApp application built with Kubernetes and Docker.

## Contributors

- Sabrina MOHAMMEDI
- Vanessa MOHAMMEDI

## Prerequisites

Before you begin, ensure you have the following installed:

- Docker
- Minikube

## Getting Started

1. **Start Docker:**
   ```bash
   start /min docker
   ```

2. **Start Minikube:**
   ```bash
   minikube start
   ```

3. **Pull Docker images:**
    ```bash
    docker pull sabrinam2/todoapp-frontendservice:latest
    docker pull sabrinam2/todoapp-taskmanagerservice:latest
    docker pull sabrinam2/todoapp-filestorageservice:latest
    ```

4. **Apply Kubernetes configurations:**
    ```bash
    ## Frontend service:
    kubectl apply -f front/frontend-deployment.yaml
    kubectl apply -f front/frontend-service.yaml
    
    ## Task Manager service:
    kubectl apply -f taskmanagerservice/taskmanagerservice-mysql-pod.yaml
    kubectl apply -f taskmanagerservice/taskmanagerservice-service.yaml
    
    ## File Storage service:
    kubectl apply -f file-storage-service/filestorageservice-mysql-pod.yaml
    kubectl apply -f file-storage-service/filestorageservice-service.yaml
    
    ## Ingress configuration:
    kubectl apply -f ingress.yaml
    
    # Enable Ingress DNS addon:
    minikube addons enable ingress-dns
    
    # Start Minikube tunnel:
    minikube tunnel
    ```

## Usage

You can access different parts of the application using the following URLs:

- **Application Frontend:** [http://todoapp.info](http://todoapp.info)
- **Task Manager Service API:** [http://todoapp.info/api/tasks](http://todoapp.info/api/tasks)
- **File Storage Service API:** [http://todoapp.info/api/files](http://todoapp.info/api/files)
