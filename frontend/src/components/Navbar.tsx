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
                console.log(data.role == "Admin");
                setState(data.role == "Admin")
            } else {
                console.log(false)
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
                            <a className="nav-link" href="/lista">Termékek</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/toggle">Make Your Own</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/novcsokk">Profil</a>
                        </li>
                        <li className="nav-item" hidden={!LoggedIn}>
                            <LogoutButton token={token}/>
                        </li>
                        <li className="nav-item" hidden={LoggedIn}>
                            <a className="nav-link" href="/rendezes">Bejelentkezés</a>
                        </li>
                        <li className="nav-item" hidden={LoggedIn}>
                            <a className="nav-link" href="/paginacio">Regisztráció</a>
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