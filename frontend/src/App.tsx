import React, { createContext, useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Profile from './components/Profile';
import Login from './components/Login';
import useToken from './auth/useToken';
import PrivateRoute from './auth/PrivateRoute';
import Listazas from './components/User/ProductListazasUser';
import { ProductFelvetel } from './components/Admin/ProductFelvetelUser';
import NavBar from './components/Navbar';
import AdminFelulet from './components/Admin/AdminFelulet';
export const AuthContext = createContext('no token');

function App() {
  const { token, setToken } = useToken();
  return (
    <AuthContext.Provider value={token}>
      <NavBar />
      <div className="wrapper">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={
              <h1>Home</h1>
            } />
            <Route path="/lista" element={
              <Listazas />
            } />
            <Route path="/productfelvetel" element={
              <ProductFelvetel />
            } />
            <Route path="/login" element={
              <Login setToken={setToken} />
            } />
            <Route path="/profile" element={
              <PrivateRoute element={
                <Profile />
              } />
            } />


            <Route path="/admin" element={
              <PrivateRoute element={
                <AdminFelulet />
              } />
            } />
          </Routes>
        </BrowserRouter>
      </div>
    </AuthContext.Provider>
  );
}
export default App;