import React, {useState, useEffect} from "react";
import axios from "axios";

import './Tasks.css';
import Item from './Item';

const Tasks = (props) => {
    const [pdfsArray, setPdfsArray]  = useState([])
    const [updateTasks, setUpdateTasks] = useState(false);

    const handleCallbackFromTask = () => {
        setUpdateTasks(!updateTasks);
    }

    useEffect(()=> {
        axios.get("http://localhost:8080/pdfs")
            .then((res) => {setPdfsArray(res.data.filesList)})
            .catch((err) => console.log(err))
    }, [props.updateTasks, updateTasks])

    const tasksList = pdfsArray.map((item) =>       
        <Item item={item} updatetasks={handleCallbackFromTask}></Item>
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