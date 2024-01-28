import React, { useState } from 'react';
import axios from "axios";
import fileStorageUrl from "../../fileStorageUrl";
import './FileItem.css';

function FileItem({ fileId, fileName, uploadDate }) {

    const baseUrl = fileStorageUrl;

    const extractedFileName = fileName.split('\\').pop().split('/').pop();
    const formattedUploadDate = new Date(uploadDate);
    const month = formattedUploadDate.getMonth() + 1;
    const day = formattedUploadDate.getDate();
    const year = formattedUploadDate.getFullYear();
    const formattedDateString = `${month}/${day}/${year}`;

    const [downloadError, setDownloadError] = useState('');

    const handleDownload = () => {
        try {
            window.open(`${baseUrl}/api/files/download/${fileId}`);
        } catch (error) {
            console.error('Error downloading file:', error);
            setDownloadError('Failed to download the file.');
        }
    };

    const handleDelete = async () => {
        try {
            await axios.delete(`${baseUrl}/api/files/${fileId}`);
            window.location.reload(false);
        } catch (error) {
            console.error('Error deleting file:', error);
        }
    };

    return (
        <div className="file">
            <div className="file-content">
                <div className="file-info">
                    <span className="fileName">{extractedFileName}</span>
                    <p className="uploadDate">Upload date: {formattedDateString}</p>
                </div>
                <div className="file-actions">
                <button className="download-button" onClick={handleDownload}>Download</button>
                    <button className="delete-button" onClick={handleDelete}>Delete</button>
                </div>
            </div>
            {downloadError && <p className="error">{downloadError}</p>}
        </div>
    );
}

export default FileItem;
