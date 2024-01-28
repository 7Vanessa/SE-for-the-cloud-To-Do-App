-- Create a new database named 'files_db'
CREATE DATABASE IF NOT EXISTS files_db;

-- Use the newly created database
USE files_db;

CREATE TABLE if NOT EXISTS file_entity (
    fileId INT AUTO_INCREMENT PRIMARY KEY,
    file MEDIUMBLOB NOT NULL,
    uploadDate DATE NOT NULL);
