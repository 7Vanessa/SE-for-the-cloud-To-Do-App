-- Create a new database named 'your_database_name'
CREATE DATABASE IF NOT EXISTS task_db;

-- Use the newly created database
USE task_db;

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(1000),
                      completed BOOLEAN
);

-- Insertion 1
INSERT INTO task (title, description, completed) VALUES
    ('Complete Research Proposal', 'Research and gather information for the upcoming project proposal. Summarize findings and prepare a draft.', false);

-- Insertion 2
INSERT INTO task (title, description, completed) VALUES
    ('Prepare Client Presentation', 'Create a compelling presentation for the client meeting. Highlight key project details and showcase potential solutions.', false);

-- Insertion 3
INSERT INTO task (title, description, completed) VALUES
    ('Submit Monthly Report', 'Compile and submit the monthly report summarizing team accomplishments, challenges, and future plans.', true);

-- Insertion 4
INSERT INTO task (title, description, completed) VALUES
    ('Review Code for Security', 'Conduct a thorough review of the codebase to identify and address any potential security vulnerabilities. Ensure compliance with security best practices.', false);

-- Insertion 5
INSERT INTO task (title, description, completed) VALUES
    ('Organize Team Building Event', 'Plan and coordinate a team-building event to foster collaboration and boost team morale. Consider different activities and venues.', true);
