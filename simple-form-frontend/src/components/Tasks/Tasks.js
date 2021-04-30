import React, {useState, useEffect} from "react";
import axios from "axios";

import './Tasks.css';
import Item from './Item';

const Tasks = () => {
    const [pdfsArray, setPdfsArray]  = useState([])

    useEffect(()=> {
        axios.get("http://localhost:8080/pdfs")
            .then((res) => setPdfsArray(res.data.filesList))
            .catch((err) => console.log(err))
    })

    const tasksList = pdfsArray.map((item) =>       
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