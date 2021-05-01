import React from 'react'
import './TaskProgressBar.css';

const Progress = (props) => {

    return (
    <div className="ItemProgress"> 
      <div className="TaskProgressBar">
        <div
          className="TaskProgress"
          style={{ width: props.progress + '%' }}
        >
            <div className="TaskName">{props.name}</div>
        </div>
      </div>
        <span className="percent"> {props.progress} % </span>
    </div>
    );

};

export default Progress