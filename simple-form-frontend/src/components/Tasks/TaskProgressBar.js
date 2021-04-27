import React from 'react'
import './TaskProgressBar.css';

const Progress = (props) => {

    return (
    <div className="ItemProgress"> 
      <div className="TaskProgressBar">
        <div
          className="TaskProgress"
          style={{ width: "50%"}} //props.progress + '%' }}
        >
            {props.name}
        </div>
      </div>
        <span className="percent">100%</span>
    </div>
    );
};

export default Progress