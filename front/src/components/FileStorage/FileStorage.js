import React, { useState, useEffect } from 'react';
import axios from 'axios';
import FileItem from "../FileItem/FileItem";
import './FileStorage.css';
import fileStorageUrl from "../../fileStorageUrl";

function FileStorage() {
    const baseUrl = fileStorageUrl;

    const [files, setFiles] = useState([]);
    const [selectedFile, setSelectedFile] = useState(null);

    useEffect(() => {
        fetchFiles();
    }, []);

    const fetchFiles = async () => {
        try {
            const response = await axios.get(`${baseUrl}/all`);
            setFiles(response.data);
        } catch (error) {
            console.error('Error fetching files:', error);
        }
    };

    const handleFileChange = (event) => {
        const selected = event.target.files[0];
        setSelectedFile(selected);
    };

    const handleFileUpload = async () => {
        if (!selectedFile) {
            alert("Please select a file to upload.");
            return;
        }

        const formData = new FormData();
        formData.append('file', selectedFile);

        try {
            await axios.post(`${baseUrl}/upload`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            setSelectedFile(null);
            fetchFiles();
        } catch (error) {
            console.error('Error uploading file:', error);
            alert('Failed to upload file.');
        }
    };

    return (
        <div className="file-storage-container">
            <h2>File Storage</h2>
            <div className="upload-form">
                <input className="file-input" type="file" onChange={handleFileChange} />
                <button className="upload-button" onClick={handleFileUpload}>Upload File</button>
            </div>
            <div className="file-list">
                {files.length === 0 ? (
                    <p>No files available</p>
                ) : (
                    <div>
                        {files.map((file) => (
                            <FileItem key={file.fileId} fileId={file.fileId} fileName={file.fileName} uploadDate={file.uploadDate} />
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}

export default FileStorage;
