FROM mysql:latest

COPY ./db-init-scripts/ /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD: root
ENV MYSQL_DATABASE: task_db