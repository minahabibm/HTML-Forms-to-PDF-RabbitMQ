import React, {useState, useRef } from 'react'
import './DropZone.css'

const Dropzone = (props) => {
    const [hightlight, sethightlight] = useState(false);
    const fileInputRef = useRef(null);

    const openFileDialog = () => {
        if (props.disabled) return;
        fileInputRef.current.click();
    }

   const onFilesAdded = (evt) => {
        if (props.disabled) return;
        const files = evt.target.files;
        if (props.onFilesAdded) {
          const array = fileListToArray(files);
          props.onFilesAdded(array);
        }
    }

    const fileListToArray = (list) => {
        const array = [];
        for (var i = 0; i < list.length; i++) {
          array.push(list.item(i));
        }
        return array;
    }

    const onDragOver = (evt) => {
        evt.preventDefault();
      
        if (props.disabled) return;
        sethightlight(true);      
    }

    const onDragLeave = () => {
        sethightlight(false);      
    }

    const onDrop = (event) => {
        event.preventDefault();
        // to check of file type.
        for (const item in event.dataTransfer.files) {
            if (item !== "length"  && item !== "item") {
                var str = JSON.stringify(event.dataTransfer.files[item].type);
                if (str.indexOf('image') === -1) {
                    sethightlight(false);
                    alert("Only Images Allowed")
                    return
                }
            }
        }
        if (props.disabled) return;
      
        const files = event.dataTransfer.files;
        if (props.onFilesAdded) {
          const array = fileListToArray(files);
          props.onFilesAdded(array);
        }
        sethightlight(false); 
    }
    
    return (
        <div>
            <div 
                className={`Dropzone ${hightlight ? "Highlight" : ""}`}
                onDragOver={onDragOver}
                onDragLeave={onDragLeave}
                onDrop={onDrop}
                onClick={openFileDialog}
                style={{ cursor: props.disabled ? "default" : "pointer" }}
            >
                <img
                    alt="upload"
                    className="Icon"
                    src="https://img.icons8.com/clouds/100/000000/upload-to-cloud.png"
                />
                 <input
                    ref={fileInputRef}
                    className="FileInput"
                    type="file"
                    multiple
                    accept="image/*"
                    onChange={onFilesAdded}
                />
                <span>Select Image</span>
            </div>   
        </div>     
    );
};

export default Dropzone;
