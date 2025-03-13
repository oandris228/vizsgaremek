import React, { createContext, useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Profile from './components/Profile';
import Login from './components/Login';
import useToken from './auth/useToken';
import PrivateRoute from './auth/PrivateRoute';
import Listazas from './components/User/ProductListazasUser';
import { ProductFelvetel } from './components/Admin/Products';
import NavBar from './components/Navbar';
import AdminFelulet from './components/Admin/AdminFelulet';
import AdminNavBar from './components/Admin/AdminNavbar';
import Users from './components/Admin/Users';
import { Regisztracio } from './components/Register';
import Modify from './components/Admin/modify';
export const AuthContext = createContext('no token');

function App() {
  const { token, setToken } = useToken();
  return (
    <AuthContext.Provider value={token}>
      <NavBar />
      <div className="wrapper">
        <BrowserRouter>
          <Routes>
            {/* User Authentication/Creation */}
            <Route path="/login" element={
              <Login setToken={setToken} />
            } />
            <Route path="/profile" element={
              <PrivateRoute element={
                <Profile />
              } />
            } />
            <Route path="/register" element={
              <>
                <h1>Regisztráció</h1>
                <Regisztracio />
              </>
            } />

            {/* User Accessible endpoints */}

            <Route path="/" element={
              <h1>Home</h1>
            } />
            <Route path="/shop" element={
              <Listazas />
            } />
            <Route path="/custom" element={ /* lol, lmao even (never implementing this) */
              <h1>Saját blend készítő</h1>
            } />


            {/* Admin endpoints */}


            <Route path="/admin" element={
              <PrivateRoute element={
                <>
                  <AdminNavBar />
                  <AdminFelulet />
                </>
              } />
            } />
            <Route path="/products" element={
              <PrivateRoute element={
                <>
                  <AdminNavBar />
                  <ProductFelvetel />
                </>
              } />
            } />
            <Route path="/users" element={
              <PrivateRoute element={
                <>
                  <AdminNavBar />
                  <Users />
                </>
              } />
            } />
            <Route path="/orders" element={
              <>
                <AdminNavBar />
                <h1>Rendelések kezelése</h1>
              </>
            } />

            <Route path="/edit/:id" element={
              <>
                <AdminNavBar />
                <Modify/>
              </>
            } />
          </Routes>
        </BrowserRouter>
      </div>
    </AuthContext.Provider>
  );
}
export default App;