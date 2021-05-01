import React, {useState, useEffect} from 'react';
import './TaskProgressBar.css';

const Progress = (props) => {
  const [progress, setProgress] = useState(props.progress)
  // useEffect(() =>{
  //   if (progress !== 100) {
  //     setProgress(100)
  //   }
  // });

  return (
    <div className="ItemProgress"> 
      <div className="TaskProgressBar">
        <div
          className="TaskProgress"
          style={{ width: `${progress}%` }}
        >
            <div className="TaskName">{progress}</div>
        </div>
      </div>
        <span className="percent"> {progress} % </span>
    </div>
    );

};

export default Progress