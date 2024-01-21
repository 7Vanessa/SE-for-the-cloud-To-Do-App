import React from 'react';
import TodoItem from "../components/ToDoItem/ToDoItem";
import ToDoList from "../components/ToDoList/ToDoList";
function HomePage({ task, deleteTask, toggleCompleted }) {

    return (
        <div>
            <ToDoList/>
        </div>
    );
}
export default HomePage;
