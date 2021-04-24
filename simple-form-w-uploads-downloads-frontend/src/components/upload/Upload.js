import React, { useState } from "react";
import axios from "axios";

import './Upload.css';
import DropZone from './DropZone';
import ProgressBar from './ProgressBarUpload';

const Upload = (props) => {
    const [files, setFiles] = useState([]);
    const [busy, setBusy]= useState(false);
    const [uploading, setUploading] = useState(false);
    const [uploadProgress, setUploadProgress] = useState({});
    const [successfullUploaded, setSuccessfullUploaded] = useState(false);

    const onFilesAdded = (filetoAdd) => {
        setFiles([...files, ...filetoAdd]);
        setBusy(true);
    }

    const renderProgress = (file) => {
        const uploadProgressForFile = uploadProgress[file.name];
        if (uploading || successfullUploaded) {
            return (
                <div className="ProgressWrapper">
                    <ProgressBar progress={uploadProgressForFile ? uploadProgressForFile.percentage : 0} />
                    <img
                        className="CheckIcon"
                        alt="done"
                        src="https://img.icons8.com/flat-round/22/000000/checkmark.png"
                        style={{
                            opacity:
                            uploadProgressForFile && uploadProgressForFile.state === "done" ? 1 : 0
                            }}
                    />
                </div>
            );
        }
    }

    const renderActions = () => {
        return (
            <div>
                <button 
                    className="buttonUpload"
                    onClick={() => {
                        setFiles([])
                        setUploadProgress({})
                        setBusy(false)
                        successfullUploaded && props.callbackFromUpload(null)
                        setSuccessfullUploaded(false)
                    }}
                    disabled = {uploading}
                >
                    { successfullUploaded ? 'Remove' : 'Clear'}
                </button>
                <button 
                    className="buttonUpload"
                    disabled={files.length < 0 || uploading || successfullUploaded }
                    onClick={uploadFiles}
                >
                    Upload
                </button>
            </div>
        );
    }

   const uploadFiles = async () => {
        setUploading(true);
        setUploadProgress({});
        const promises = [];
        files.forEach(file => {
            promises.push(sendRequest(file));
        });
        try {
            await Promise.all(promises);
            setSuccessfullUploaded(true);
            setUploading(false);
        } catch (e) {
            // Not Production ready! Do some error handling here instead...
            console.log(e)
            setFiles([])
            setUploadProgress({})
            setBusy(false)
            setSuccessfullUploaded(false)
            setUploading(false);
        }
    }

    const sendRequest = (file) => {
        return new Promise((resolve, reject) => {

            let data = new FormData()
            data.append('file', files[0])

            var config = {
                onUploadProgress: function(progressEvent) {
                    var percentCompleted = Math.round( (progressEvent.loaded * 100) / progressEvent.total );
                    const copy = {};
                    copy[file.name] = {
                        state: "pending",
                        percentage: percentCompleted
                    };
                    setUploadProgress(copy);
                }
            };

            axios.post('http://localhost:5000/upload', data, config)
            .then(function (res) {
                setUploadProgress(prevState => ({
                    ...prevState,
                    [file.name]: {state: "done", percentage: 100}
                }));
                console.log(res);
                props.callbackFromUpload({
                    "name": "image", 
                    "URL": "image URL"
                })
                resolve(res);
            })
            .catch(function (err) {
                if (err.response) {
                    // client received an error response (5xx, 4xx)
                    setUploadProgress(prevState => ({
                        ...prevState,
                        [file.name]: {state: "error", percentage: 0}
                    }));
                    console.log("error from response")
                    reject(err);
                } else if (err.request) {
                    // client never received a response, or request never left
                    console.log("error from request")
                    reject(err);
                } else {
                    // anything else
                    console.log("other error")
                    reject(err);
                } 
            });
        });
    }

    return (
      <div className="Upload">

        <div className="Content">
            <div>
                <DropZone 
                    onFilesAdded={onFilesAdded}
                    disabled={uploading || successfullUploaded || busy}
                />
            </div>
            <div className="Files">
                {files.map(file => {
                    return ( 
                    <div key={file.name} >
                        <div className="Row">
                            <span className="Filename">{file.name}</span>
                            <span className="Actions"> {renderActions()} </span>
                       </div>
                       {renderProgress(file)}
                    </div>
                    );
                })}
            </div>
        </div> 
    </div>  
  );
};

export default Upload;