import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../App';
import LogoutButton from '../auth/LogoutButton';

export default function NavBar() {
    const token = useContext(AuthContext);

    const [state, setState] = useState(false);

    const LoggedIn = token != undefined;
    
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

    return <>
        <div className="container">
            <nav className="">
                <a className="" href="#">Navbar</a>

                <div className="" id="">
                    <ul className="">
                        <li className="nav-item active">
                            <a className="nav-link" href="/">Főoldal</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/shop">Termékek</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/custom">Make Your Own</a> {/*lmao */}
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/profile">Profil</a>
                        </li>
                        <li className="nav-item" hidden={!LoggedIn}>
                            <LogoutButton token={token}/>
                        </li>
                        <li className="nav-item" hidden={LoggedIn}>
                            <a className="nav-link" href="/login">Bejelentkezés</a>
                        </li>
                        <li className="nav-item" hidden={LoggedIn}>
                            <a className="nav-link" href="/register">Regisztráció</a>
                        </li>
                        <li className="nav-item" hidden={!state}>
                            <a className="nav-link" href="/admin">Admin</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </>
}