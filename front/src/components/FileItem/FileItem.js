import React from 'react';
import './FileItem.css';

function FileItem({ fileName, uploadDate }) {
    return (
        <div className="file">
            <h3 className="fileName">File Details</h3>
            <p><span className="fileName">File Name:</span> {fileName}</p>
            <p><span className="uploadDate">Upload Date:</span> {uploadDate}</p>
        </div>
    );
}

export default FileItem;
