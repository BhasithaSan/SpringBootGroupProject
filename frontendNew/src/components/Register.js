import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./styles.css";

const Register = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [telephone, setTelephone] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState(""); // State for API message

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:9000/Api/register", {
        "email":email,
        "name":name,
        "contactNo":telephone,
        "password":password,
      });
      setMessage(response.data.message || "Registration unsuccessful!"); // Display API message or default
      // Clear input fields
      setEmail("");
      setName("");
      setTelephone("");
      setPassword("");
    } catch (error) {
      const errorMsg =
        error.response?.data?.message ||
        "Registration failed. Please try again.";
      setMessage(errorMsg);
      console.error(error);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="heading">Register</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group custom-form-group">
          <label>Email:</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group custom-form-group">
          <label>Name:</label>
          <input
            type="text"
            className="form-control"
            value={name}
            name="name"
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="form-group custom-form-group">
          <label>Telephone:</label>
          <input
            type="tel"
            className="form-control"
            value={telephone}
            name="contactNo"
            onChange={(e) => setTelephone(e.target.value)}
            required
          />
        </div>
        <div className="form-group custom-form-group">
          <label>Password:</label>
          <div className="input-group">
            <input
              type={showPassword ? "text" : "password"}
              className="form-control"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <div className="input-group-append">
              <span className="input-group-text">
                <i
                  className={`fas ${showPassword ? "fa-eye" : "fa-eye-slash"}`}
                  onClick={() => setShowPassword(!showPassword)}
                  style={{ cursor: "pointer" }}
                ></i>
              </span>
            </div>
          </div>
        </div>
        <button type="submit" className="btn btn-primary custom-btn">
          Register
        </button>
        {message && (
          <div
            className={`alert ${
              message.includes("successful") ? "alert-success" : "alert-danger"
            } mt-3`}
          >
            {message}
          </div>
        )}
      </form>
      <p className="mt-3">
        Already have an account? <Link to="/login">Login here</Link>
      </p>
    </div>
  );
};

export default Register;
