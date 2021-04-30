import './App.css';
import Form from './components/Form/Form';
import Tasks from './components/Tasks/Tasks';

// TODO Add a call back to to form to reload tasks.

function App() {
  return (
    <div className="App">
      <Form></Form>
      <Tasks></Tasks>
    </div>
  );
}

export default App;
