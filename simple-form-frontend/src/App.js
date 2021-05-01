import React, {useState} from "react";
import Form from './components/Form/Form';
import Tasks from './components/Tasks/Tasks';

import './App.css';
// TODO Form Checks before submission

function App() {
  const [updateTask, setUpdateTasks] = useState(false);
  const handleCallbackFromForm = () => {
    setUpdateTasks(!updateTask);
  }

  return (
    <div className="App">
      <Form callbackFromForm={handleCallbackFromForm}></Form>
      <Tasks updateTasks={updateTask} ></Tasks>
    </div>
  );
}

export default App;