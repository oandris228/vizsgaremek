import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../App';
import { NavLink, useNavigate } from 'react-router-dom';
import { GetProfile, LogoutUser } from '../functions';
import { User } from '../types';

export default function NavBar() {
    const token = useContext(AuthContext);
    const navigate = useNavigate();

    const [state, setState] = useState(false);
    const [isOpen, setIsOpen] = useState(false);
    const [user, setUser] = useState<User>();

    const [LoggedIn, setLoggedIn] = useState(token != undefined);

    useEffect(() => {
        async function IsUserAdmin() {

            if (LoggedIn) {
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
        GetProfile(token).then((e) => setUser(e))
    }, [token])

    function handleLogout() {
        LogoutUser(token);
        setLoggedIn(false);
    }

    return <>
        <nav className="navbar">
            <NavLink className="nav-title" to="/">Tea Shop</NavLink>

            <ul className="nav-list">
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
                    <NavLink className="nav-link" to={`/cart/${user?.id}`}>Kosár</NavLink>
                </li>
                {state && (
                    <li>
                        <NavLink className="nav-link nav-admin" to="/admin">Admin</NavLink>
                    </li>
                )}
                {LoggedIn ? (
                    <li>
                        <NavLink className="nav-link" onClick={() => { handleLogout();}} to="/login">Logout</NavLink>
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
            <button
                className="hamburger-button"
                onClick={() => setIsOpen(!isOpen)}
            >
                {isOpen ? <p>a</p> : <p>b</p>}
            </button>
        </nav>
        {isOpen && (
                <ul className="nav-list-mobile">
                    <li>
                        <NavLink className="nav-mobile" to="/">Főoldal</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-mobile" to="/custom">Make Your Own</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-mobile" to="/profile">Profil</NavLink>
                    </li>
                    <li>
                        <NavLink className="nav-mobile" to={`/cart/${user?.id}`}>Kosár</NavLink>
                    </li>
                    {state && (
                        <li className='nav-admin'>
                            <NavLink className="nav-mobile nav-admin" to="/admin">Admin</NavLink>
                        </li>
                    )}
                    {LoggedIn ? (
                        <li>
                            <button className="nav-mobile" onClick={() => { handleLogout(); navigate('/login') }}>Logout</button>
                        </li>
                    ) : (
                        <>
                            <li>
                                <NavLink className="nav-mobile" to="/login">Bejelentkezés</NavLink>
                            </li>
                            <li>
                                <NavLink className="nav-mobile" to="/register">Regisztráció</NavLink>
                            </li>
                        </>
                    )}
                </ul>
            )}
    </>
}