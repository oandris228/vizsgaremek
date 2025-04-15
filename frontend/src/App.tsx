import React, { createContext, useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Profile from './components/Profile';
import Login from './components/Login';
import Listazas from './components/User/ProductListazasUser';
import { Products } from './components/Admin/Products/Products';
import NavBar from './components/Navbar';
import AdminFelulet from './components/Admin/AdminFelulet';
import Users from './components/Admin/Users/Users';
import { Regisztracio } from './components/Register';
import ModifyProducts from './components/Admin/Products/ModifyProducts';
import ModifyUsers from './components/Admin/Users/ModifyUsers';
import { Cart } from './components/Admin/Users/Cart';
import { Home } from './components/Home';
import { AuthProvider } from './auth/AuthContext';

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <div className="min-h-screen min-w-screen flex flex-col bg-mainbackground">
          <Routes>
            {/* User Authentication/Creation */}
            <Route path="/login" element={
              <><NavBar /><Login /></>
            } />
            <Route path="/profile" element={
              <><NavBar /><Profile /></>
            } />
            <Route path="/register" element={
              <>
                <NavBar />
                <Regisztracio />
              </>
            } />

            {/* User Accessible endpoints */}

            <Route path="/" element={
              <>
                <NavBar />
                <div className='default-wrapper'>
                  <Home />
                </div>
              </>
            } />
            <Route path="/shop" element={
              <><NavBar />
                <div className='default-wrapper'>
                  <Listazas />
                </div>
              </>
            } />
            <Route path="/custom" element={ /* lol, lmao even (never implementing this) */
              <><NavBar />
                <div className='default-wrapper'>
                  <h1>Saját blend készítő</h1>
                </div>
              </>
            } />
            <Route path="/cart/:user_id" element={
              <><NavBar />
                <div className='default-wrapper'>
                  <Cart />
                </div>
              </>
            } />

            {/* Admin endpoints */}


            <Route path="/admin" element={
              <>
                <NavBar />
                <AdminFelulet />
              </>
            } />

            {/* Product management */}

            <Route path="/products" element={
              <>
                <Products />
              </>
            } />
            <Route path="/products/edit/:id" element={
              <>
                <ModifyProducts />
              </>
            } />

            {/* User management */}

            <Route path="/users" element={
              <>
                <Users />
              </>
            } />
            <Route path="/users/edit/:id" element={
              <>
                <ModifyUsers />
              </>
            } />

            {/* Order management */}

            <Route path="/orders" element={
              <>
                <h1>Rendelések kezelése TODO</h1>
              </>
            } />
            <Route path="/ordwers/edit/:id" element={
              <>
                <h1>Rendelések módosítása TODO</h1>
              </>
            } />
          </Routes>
        </div>
      </AuthProvider>
    </BrowserRouter>
  );
}
export default App;