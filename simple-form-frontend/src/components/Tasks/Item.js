import React from "react";

import './Item.css';
import TaskItemProgress from './TaskProgressBar';

const Item = (props) => {

  return (
    <li key= {props.item.key}>
        <div className="itemDiv">
            <div className="itemprogress">
                {!props.item.progress ? 
                    <div>
                        <h5 className="itemtitle">{props.item.item}</h5>
                    </div>
                    :
                    <div>
                        <TaskItemProgress 
                            name={props.item.item}  
                            progress= {1}
                        ></TaskItemProgress>
                    </div>
                }
            </div>

            <div>
                {!props.item.progress && 
                    <>
                        <button className="buttonTasks">Download</button>
                        <button className="buttonTasks">Delete</button>
                    </>
                }
            </div>
        </div>
        <hr className={"itemSep"}></hr>
    </li>
        
  );
};

export default Item;