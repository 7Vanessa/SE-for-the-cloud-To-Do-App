// TodoList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TodoItem from "../ToDoItem/ToDoItem";
import './ToDoList.css';

function TodoList() {
    const [tasks, setTasks] = useState([]);
    const [text, setText] = useState('');
    const [description, setDescription] = useState('');
    const [dueDate, setDueDate] = useState('');

    const baseUrl = 'http://localhost:8081';

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {
        try {
            const response = await axios.get(`${baseUrl}/api/tasks`);
            setTasks(response.data);
        } catch (error) {
            console.error('Error fetching tasks:', error);
        }
    };

    const addTask = async () => {
        try {
            const formattedDueDate = dueDate
                ? new Date(`${dueDate} 00:00:00`).toISOString().slice(0, 19).replace('T', ' ')
                : null;

            console.log('formattedDueDate:', formattedDueDate);

            const newTask = {
                title: text,
                description: description,
                due_date: new Date(),
                completed: false,
            };

            await axios.post(`${baseUrl}/api/tasks`, newTask);
            fetchTasks();
            setText('');
            setDescription('');
            setDueDate('');
        } catch (error) {
            console.error('Error adding task:', error);
        }
    };




    const deleteTask = async (id) => {
        try {
            await axios.delete(`${baseUrl}/api/tasks/${id}`);
            fetchTasks();
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const updateTask = (updatedTask) => {
        setTasks(tasks.map(task => (task.id === updatedTask.id ? updatedTask : task)));
    };

    const toggleCompleted = async (id) => {
        try {
            const updatedTask = tasks.find((task) => task.id === id);
            updatedTask.completed = !updatedTask.completed;
            await axios.put(`${baseUrl}/api/tasks/${id}`, updatedTask);
            fetchTasks();
        } catch (error) {
            console.error('Error toggling task completion:', error);
        }
    };

    return (
        <div className="todo-list">
            {tasks.map(task => (
                <TodoItem
                    key={task.id}
                    task={task}
                    updateTask={updateTask}
                    deleteTask={deleteTask}
                    toggleCompleted={toggleCompleted}
                    fetchTasks={fetchTasks}  // Passer la fonction fetchTasks à TodoItem
                />
            ))}
            <div className="add-task-container">
                <input
                    placeholder="Title"
                    value={text}
                    onChange={e => setText(e.target.value)}
                />
                <input
                    placeholder="Description"
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                />
                <input
                    type="date"
                    placeholder="Due Date"
                    value={dueDate}
                    onChange={e => setDueDate(e.target.value)}
                />
                <button onClick={addTask}>Add</button>
            </div>
        </div>
    );
}

export default TodoList;
