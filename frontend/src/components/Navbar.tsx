import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../App';
import { NavLink, useNavigate } from 'react-router-dom';
import { LogoutUser } from '../functions';

export default function NavBar() {
    const token = useContext(AuthContext);
    const navigate = useNavigate();

    const [state, setState] = useState(false);

    const [LoggedIn, setLoggedIn] = useState(token != undefined);
    
    useEffect(()=> {
        async function IsUserAdmin() {

            if(LoggedIn) {
                const response = await fetch('http://localhost:3000/users/token', {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + token
                    }
                })
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                setState(data.role == "Admin")
            } else {
                setState(false)
            }
        }
        IsUserAdmin();
    }, [token])

    function handleLogout() {
        LogoutUser(token);
        setLoggedIn(false);
    }

    return <>
            <nav className="flex items-center justify-between bg-navbar p-4 w-full top-0 start-0">
                <NavLink className="text-xl font-bold" to="/">Navbar</NavLink>
                
                <ul className="flex space-x-4">
                    <li>
                        <NavLink className="nav-link" to="/">Főoldal</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-link" to="/custom">Make Your Own</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-link" to="/profile">Profil</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-link" to="/cart">Kosár</NavLink>
                    </li>
                    {state && (
                        <li>
                            <NavLink className="nav-link" to="/admin">Admin</NavLink>
                        </li>
                    )}
                    {LoggedIn ? (
                        <li>
                             <button className="nav-link" onClick={()=> {handleLogout(); navigate('/login')}}>Logout</button>
                        </li>
                    ) : (
                        <>
                            <li>
                                <NavLink className="nav-link" to="/login">Bejelentkezés</NavLink>
                            </li>
                            <li>
                                <NavLink className="nav-link" to="/register">Regisztráció</NavLink>
                            </li>
                        </>
                    )}
                </ul>
            </nav>
    </>
}