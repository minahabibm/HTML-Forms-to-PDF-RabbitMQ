import React, {useState, useEffect} from "react";
import axios from "axios";

import './Item.css';
import TaskItemProgress from './TaskProgressBar';

const Item = (props) => {

    const [downloadClicked, setsetDownloadClicked]  = useState(false)
    const [deleteClicked, setDeleteClicked]  = useState(false)

    useEffect(() => {
        if (downloadClicked ) {
            axios({
                url: `http://localhost:8080/download/${props.item.uuid}`,
                method: 'GET',
                responseType: 'blob', // important
              }).then((response) => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', `${props.item.name}.pdf`);
                document.body.appendChild(link);
                link.click();
              });
              setsetDownloadClicked(false)
        }
    })

    useEffect(() => {
        if (deleteClicked) {
            axios.delete(`http://localhost:8080/pdfs/${props.item.uuid}`).catch((err) => console.log(err))
            setDeleteClicked(false)
        }
      })

    const downloadFile = () => {
        setsetDownloadClicked(true)
    }
    const deleteFile = () => {
        setDeleteClicked(true)
    }

  return (
    <li key= {props.item.uuid}>
        {/* {console.log(props.item.progress)} */}
        <div className="itemDiv">
            <div className="itemprogress">
                {props.item.isReady ? 
                    <div>
                        <h5 className="itemtitle">{props.item.name}</h5>
                    </div>
                    :
                    <div>
                        <TaskItemProgress 
                            name={props.item.name}  
                            progress= {props.item.progress}
                            uid = {props.item.uuid}
                        ></TaskItemProgress>
                    </div>
                }
            </div>

            <div>
                {props.item.isReady && 
                    <>
                        <button 
                            className="buttonTasks"
                            onClick={() => downloadFile()}
                        >Download</button>
                        <button 
                            className="buttonTasks"
                            onClick={() => deleteFile()}
                        >Delete</button>
                    </>
                }
            </div>
        </div>
        <hr className={"itemSep"}></hr>
    </li>
        
  );
};

export default Item;