import React from "react";

import './Tasks.css';
import Item from './Item';

const Tasks = () => {
    const test = [
        {"item": "helloworld", "progress": true ,"key": 1}, 
        {"item": "helloworld", "progress": true , "key": 2}, 
        {"item": "helloworld", "progress": false , "key": 3}, 
        {"item": "helloworld", "progress": true , "key": 4},
        {"item": "helloworld", "progress": true , "key": 5},
        {"item": "helloworld", "progress": false , "key": 6},
        {"item": "helloworld", "progress": true , "key": 7},
    ]
    const tasksList = test.map((item) =>       
        <Item item={item}></Item>
    );

  return (
    <div className="divTasks">
        
        <div className= "sticky">
            <h3 className="headerCus">Tasks</h3>
        </div>
      
        <div className= "tasksdiv">
            <ul>
                {tasksList}
            </ul>
        </div>

    </div>
  );
};

export default Tasks;