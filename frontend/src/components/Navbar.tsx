import { useContext, useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { AuthContext } from '../auth/AuthContext';

export default function NavBar() {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const [state, setState] = useState(false);
    const [isOpen, setIsOpen] = useState(false);

    useEffect(() => {
        if (user) {
            if (user.role == "Admin") {
                setState(true);
            } else {
                navigate('/');
            }
        } else {
            setState(false);
            navigate('/');
        }
    }, [user])

    function handleLogout() {
        logout();
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
                {user ? (
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
                Menü
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
                    {user ? (
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