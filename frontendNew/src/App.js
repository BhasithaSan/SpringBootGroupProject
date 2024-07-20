import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import "./App.css";
import Login from "./components/LogIn";
import Register from "./components/Register";

const App = () => {
  return (
    <Router>
      <div className="App">
        <h1>Authentication</h1>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/"
            element={
              <div>
                <Link
                  to="/login"
                  target="_blank"
                  className="btn btn-primary m-2"
                >
                  Login
                </Link>
                <Link
                  to="/register"
                  target="_blank"
                  className="btn btn-secondary m-2"
                >
                  Register
                </Link>
              </div>
            }
          />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
