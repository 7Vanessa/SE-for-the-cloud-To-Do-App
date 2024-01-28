CREATE DATABASE IF NOT EXISTS files_db;

USE files_db;

CREATE TABLE if NOT EXISTS file_entity
(
    fileId     INT AUTO_INCREMENT PRIMARY KEY,
    fileName   VARCHAR(255) NOT NULL,
    file       MEDIUMBLOB   NOT NULL,
    uploadDate DATE         NOT NULL
);
