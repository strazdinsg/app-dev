import "./App.css";
import { Navigation } from "./components/Navigation";
import { BrowserRouter as Router } from "react-router-dom";
import { MainContainer } from "./components/MainContainer";

/**
 * The entrypoint for the application.
 * @return {JSX.Element}
 * @constructor
 */
function App() {
  return (
    <Router>
      <Navigation />
      <MainContainer />
    </Router>
  );
}

export default App;
