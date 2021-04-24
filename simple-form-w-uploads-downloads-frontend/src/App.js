import './App.css';
import Form from './components/Form/Form';
import Tasks from './components/Tasks/Tasks';

// TODO Add ENDPoint to remove a file at path in case of uploaded file.

function App() {
  return (
    <div className="App">
      <Form></Form>
      <Tasks></Tasks>
    </div>
  );
}

export default App;
