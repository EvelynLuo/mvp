import "./App.css";
import SearchTextField from "./component/SearchTextField";
import ButtonAppBar from "./surface/AppBar";

function App() {
  return (
    <div className="App">
      <ButtonAppBar />
      <SearchTextField />
    </div>
  );
}

export default App;
