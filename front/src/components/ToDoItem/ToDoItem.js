// TodoItem.js
import React from 'react';
import axios from 'axios';
import './ToDoItem.css';
import taskManagerUrl from "../../taskManagerUrl";

function TodoItem({ task, updateTask, fetchTasks }) {
    const baseUrl = taskManagerUrl;

    const handleToggleCompleted = async () => {
        try {
            const updatedTask = { ...task, completed: !task.completed };
            await axios.put(`${baseUrl}/${task.id}`, updatedTask);
            updateTask(updatedTask);
        } catch (error) {
            console.error('Error toggling task completion:', error);
        }
    };

    const handleDeleteTask = async () => {
        try {
            await axios.delete(`${baseUrl}/${task.id}`);
            fetchTasks();
        } catch (error) {
            console.error('Error deleting task:', error);
        }
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
            </div>
            <button className={`delete-button ${task.completed ? 'completed' : ''}`} onClick={handleDeleteTask}>Delete</button>
        </div>
    );
}

export default TodoItem;
