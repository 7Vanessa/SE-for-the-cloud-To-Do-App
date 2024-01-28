import React, { useState } from 'react';
import TodoList from "../components/ToDoList/ToDoList";
import FileStorage from "../components/FileStorage/FileStorage";

const styles = {
    homePage: {
        fontFamily: 'Arial, sans-serif',
        padding: '20px',
    },
    nav: {
        marginBottom: '20px',
    },
    button: {
        backgroundColor: '#007bff',
        border: 'none',
        color: 'white',
        padding: '10px 20px',
        cursor: 'pointer',
        borderRadius: '5px',
        marginRight: '10px',
    },
    activeButton: {
        backgroundColor: '#0056b3',
    },
    content: {
        border: '1px solid #ccc',
        padding: '20px',
        borderRadius: '5px',
    },
};

function HomePage() {
    const [activeTab, setActiveTab] = useState('Tasks');

    const handleTabChange = (tab) => {
        setActiveTab(tab);
    };

    return (
        <div style={styles.homePage}>
            <nav style={styles.nav}>
                <button style={activeTab === 'Tasks' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => handleTabChange('Tasks')}>Tasks</button>
                <button style={activeTab === 'File Storage' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => handleTabChange('File Storage')}>File Storage</button>
            </nav>
            <div style={styles.content}>
                {activeTab === 'Tasks' && <TodoList />}
                {activeTab === 'File Storage' && <FileStorage />}
            </div>
        </div>
    );
}

export default HomePage;
