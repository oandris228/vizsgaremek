import React, { createContext, useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Profile from './components/Profile';
import Login from './components/Login';
import useToken from './auth/useToken';
import PrivateRoute from './auth/PrivateRoute';
import Listazas from './components/User/ProductListazasUser';
import { Products } from './components/Admin/Products/Products';
import NavBar from './components/Navbar';
import AdminFelulet from './components/Admin/AdminFelulet';
import AdminNavBar from './components/Admin/AdminNavbar';
import Users from './components/Admin/Users/Users';
import { Regisztracio } from './components/Register';
import Modify from './components/Admin/Products/ModifyProducts';
import ModifyProducts from './components/Admin/Products/ModifyProducts';
import ModifyUsers from './components/Admin/Users/ModifyUsers';
import { Cart } from './components/Admin/Users/Cart';
export const AuthContext = createContext('no token');

function App() {
  const { token, setToken } = useToken();
  return (
    <AuthContext.Provider value={token}>
      <div className="wrapper">
        <BrowserRouter>
          <Routes>
            {/* User Authentication/Creation */}
            <Route path="/login" element={
              <><NavBar /><Login setToken={setToken} /></>
            } />
            <Route path="/profile" element={
              <PrivateRoute element={
                <><NavBar /><Profile /></>
              } />
            } />
            <Route path="/register" element={
              <>
                <h1>Regisztráció</h1>
                <NavBar />
                <Regisztracio />
              </>
            } />

            {/* User Accessible endpoints */}

            <Route path="/" element={
              <><NavBar /><h1>Home</h1></>
            } />
            <Route path="/shop" element={
              <><NavBar /><Listazas /></>
            } />
            <Route path="/custom" element={ /* lol, lmao even (never implementing this) */
              <><NavBar /><h1>Saját blend készítő</h1></>
            } />
            <Route path="/cart" element={
              <PrivateRoute element={
                <><NavBar /><Cart /></>
              } />
            } />

            {/* Admin endpoints */}


            <Route path="/admin" element={
              <PrivateRoute element={
                <>
                  <NavBar />
                  <AdminNavBar />
                  <AdminFelulet />
                </>
              } />
            } />

            {/* Product management */}

            <Route path="/products" element={
              <PrivateRoute element={
                <>
                  <NavBar />
                  <AdminNavBar />
                  <Products />
                </>
              } />
            } />
            <Route path="/products/edit/:id" element={
              <>
                <NavBar />
                <AdminNavBar />
                <ModifyProducts />
              </>
            } />

            {/* User management */}

            <Route path="/users" element={
              <PrivateRoute element={
                <>
                  <NavBar />
                  <AdminNavBar />
                  <Users />
                </>
              } />
            } />
            <Route path="/users/edit/:id" element={
              <>
                <NavBar />
                <AdminNavBar />
                <ModifyUsers />
              </>
            } />

            {/* Order management */}

            <Route path="/orders" element={
              <>
                <NavBar />
                <AdminNavBar />
                <h1>Rendelések kezelése TODO</h1>
              </>
            } />
            <Route path="/ordwers/edit/:id" element={
              <>
                <NavBar />
                <AdminNavBar />
                <h1>Rendelések módosítása TODO</h1>
              </>
            } />
          </Routes>
        </BrowserRouter>
      </div>
    </AuthContext.Provider>
  );
}
export default App;