import React, { useState, useEffect } from 'react';
import axios from 'axios';
import FileItem from "../FileItem/FileItem";

function FileStorage() {
    const [files, setFiles] = useState([]);

    useEffect(() => {
        fetchFiles();
    }, []);

    const fetchFiles = async () => {
        try {
            const response = await axios.get('/file');
            setFiles(response.data);
        } catch (error) {
            console.error('Error fetching files:', error);
        }
    };

    return (
        <div>
            <h2>File Storage</h2>
            {files.length === 0 ? (
                <p>No files available</p>
            ) : (
                <div>
                    {files.map((file, index) => (
                        <FileItem key={index} fileName={file.name} uploadDate={file.uploadDate} />
                    ))}
                </div>
            )}
        </div>
    );
}

export default FileStorage;
