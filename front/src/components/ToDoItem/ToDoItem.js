// TodoItem.js
import React from 'react';
import axios from 'axios';
import './ToDoItem.css';

function TodoItem({ task, updateTask, fetchTasks }) {
    const baseUrl = 'http://localhost:8081';

    const handleToggleCompleted = async () => {
        try {
            const updatedTask = { ...task, completed: !task.completed };
            await axios.put(`${baseUrl}/api/tasks/${task.id}`, updatedTask);
            updateTask(updatedTask);
        } catch (error) {
            console.error('Error toggling task completion:', error);
        }
    };

    const handleDeleteTask = async () => {
        try {
            await axios.delete(`${baseUrl}/api/tasks/${task.id}`);
            fetchTasks();
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) {
            return "N/A";
        }

        const options = { day: 'numeric', month: 'numeric', year: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };


    return (
        <div className={`todo-item ${task.completed ? 'completed' : ''}`}>
            <div className="checkbox-container">
                <input
                    type="checkbox"
                    checked={task.completed}
                    onChange={handleToggleCompleted}
                />
            </div>
            <div className="task-details">
                <p><strong>{task.title}</strong></p>
                <p>{task.description}</p>
                <p><strong>Due Date:</strong> {formatDate(task.dueDate)}</p>
            </div>
            <button className={`delete-button ${task.completed ? 'completed' : ''}`} onClick={handleDeleteTask}>Delete</button>
        </div>
    );
}

export default TodoItem;
