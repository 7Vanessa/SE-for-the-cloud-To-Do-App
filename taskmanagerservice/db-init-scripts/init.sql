-- Create a new database named 'your_database_name'
CREATE DATABASE IF NOT EXISTS task_db;

-- Use the newly created database
USE task_db;

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(1000),
                      due_date DATETIME,
                      completed BOOLEAN
);

-- Insertion 1
INSERT INTO task (title, description, due_date, completed) VALUES
    ('Complete Research Proposal', 'Research and gather information for the upcoming project proposal. Summarize findings and prepare a draft.', '2023-12-15 08:00:00', false);

-- Insertion 2
INSERT INTO task (title, description, due_date, completed) VALUES
    ('Prepare Client Presentation', 'Create a compelling presentation for the client meeting. Highlight key project details and showcase potential solutions.', '2023-12-20 14:30:00', false);

-- Insertion 3
INSERT INTO task (title, description, due_date, completed) VALUES
    ('Submit Monthly Report', 'Compile and submit the monthly report summarizing team accomplishments, challenges, and future plans.', '2023-12-25 18:45:00', true);

-- Insertion 4
INSERT INTO task (title, description, due_date, completed) VALUES
    ('Review Code for Security', 'Conduct a thorough review of the codebase to identify and address any potential security vulnerabilities. Ensure compliance with security best practices.', '2023-12-28 10:00:00', false);

-- Insertion 5
INSERT INTO task (title, description, due_date, completed) VALUES
    ('Organize Team Building Event', 'Plan and coordinate a team-building event to foster collaboration and boost team morale. Consider different activities and venues.', '2023-12-30 12:15:00', true);
