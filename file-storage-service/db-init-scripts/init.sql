CREATE DATABASE IF NOT EXISTS files_db;

USE files_db;

CREATE TABLE if NOT EXISTS file_entity
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    file       MEDIUMBLOB   NOT NULL,
    date DATE         NOT NULL
);
