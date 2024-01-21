// ToDoItem.js
import React from 'react';
import './ToDoItem.css';

function TodoItem({ task, deleteTask, toggleCompleted }) {
    function handleChange() {
        toggleCompleted(task.id);
    }

    return (
        <div className={`todo-item ${task.completed ? 'completed' : ''}`}>
            <input type="checkbox" checked={task.completed} onChange={handleChange} />
            <p>{task.text}</p>
            <button onClick={() => deleteTask(task.id)}>Delete</button>
        </div>
    );
}

export default TodoItem;
