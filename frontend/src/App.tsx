import React, { useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Profile from './components/Profile';
import Login from './components/Login';
import useToken from './auth/useToken';
import PrivateRoute from './auth/PrivateRoute';

function App() {
  const { token, setToken } = useToken();
  return (
    <div className="wrapper">
      <h1>Application</h1>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <h1>Home</h1>
          }/>
          <Route path="/login" element={
            <Login setToken={setToken}/>
          }/>
          <Route path="/profile" element={
            <PrivateRoute token={token} element={
              <Profile token={token}/>
            }/>
          }/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;