import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../../App';

export default function AdminFelulet() {
    const token = useContext(AuthContext);

    const [state, setState] = useState(false);

    const [frameLink, setFrameLink] = useState('/products');

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
        <div className="container flex min-h-full overflow-hidden" hidden={!state}>
            <nav className="">
                <div className="" id="">
                    <ul className="">
                        <li className="nav-item active">
                            <button onClick={()=> {setFrameLink("/products")}}>Products</button>
                        </li>
                        <li className="nav-item active">
                            <button onClick={()=> {setFrameLink("/users")}}>Users</button>
                        </li>
                        <li className="nav-item active">
                            <button onClick={()=> {setFrameLink("/orders")}}>Orders</button>
                        </li>
                    </ul>
                </div>
            </nav>


            <iframe className='min-w-screen min-h-screen' src={frameLink}></iframe>
        </div>
    </>
}