import React, {useState, useEffect} from 'react';
import axios from "axios";

import './TaskProgressBar.css';

const Progress = (props) => {

  const [progress, setProgress] = useState(props.progress);
  const [stateFile, setStateFile] = useState(false);
  const [updateProgress, setUpdateProgress] = useState(false);

 
  useEffect(async () => {

    if (!stateFile && progress < 100) {
      setTimeout(() => {
        axios.get(`http://localhost:8080/progress/${props.uid}`)
        .then((res) => {
            setProgress(res.data.taskProgress)
            setStateFile(res.data.taskStatus)
            setUpdateProgress(!updateProgress)
        }).catch((err) => console.log(err))
      }, 500);
    }

  });

  return (
    <div className="ItemProgress"> 
      <div className="TaskProgressBar">
        <div
          className="TaskProgress"
          style={{ width: `${progress}%` }}
        >
            <div className="TaskName">{props.name}</div>
        </div>
      </div>
        <span className="percent"> {progress} % </span>
    </div>
  );

};

export default Progress