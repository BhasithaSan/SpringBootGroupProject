import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./styles.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState(""); // State for API message
  const [isSuccess, setIsSuccess] = useState(false); // State to track success status

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:9000/Api/login", {
        email,
        password,
      });
      // Handle success
      console.log(response.data);
      setMessage(response.data.message || "Login successful!"); // Display API message or default
      setIsSuccess(response.data.status === "success"); // Check if the status is success
    } catch (error) {
      // Handle error
      const errorMsg =
        error.response?.data?.message ||
        "Login failed. Please check your credentials and try again.";
      setMessage(errorMsg);
      setIsSuccess(false);
      console.error(error);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="heading">Login</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group custom-form-group">
          <label>Email:</label>
          <input
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group custom-form-group">
          <label>Password:</label>
          <input
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Login
        </button>
        {message && (
          <div
            className={`alert ${
              isSuccess ? "alert-success" : "alert-danger"
            } mt-3`}
          >
            {message}
          </div>
        )}
      </form>
      <p className="mt-3">
        Don't have an account? <Link to="/register">Sign up here</Link>
      </p>
    </div>
  );
};

export default Login;
