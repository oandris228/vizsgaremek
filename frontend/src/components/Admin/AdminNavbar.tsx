import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../../App';

export default function AdminNavBar() {
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
        <div className="container" hidden={!state}>
            <nav className="">
                <a className="" href="#">Admin Navbar</a>

                <div className="" id="">
                    <ul className="">
                        <li className="nav-item active">
                            <a className="nav-link" href="/">Admin felület</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/kereses">Termékek kezelése</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/toggle">Felhasználók kezelése</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/novcsokk">Rendelések kezelése</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </>
}