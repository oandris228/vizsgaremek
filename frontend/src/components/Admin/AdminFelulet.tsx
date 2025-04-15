import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../../auth/AuthContext';
import { useNavigate } from 'react-router-dom';

export default function AdminFelulet() {
    const navigate = useNavigate();
    const { user } = useContext(AuthContext);
    const [state, setState] = useState(false);
    const [frameLink, setFrameLink] = useState('/products');

    useEffect(() => {
        if (user) {
            if (user.role == "Admin") {
                setState(true);
            } else {
                navigate('/');
            }
        } else {
            navigate('/');
        }
    },[user])

    return <>
        <div className="admin-container" hidden={!state}>
            <nav className="admin-navbar">
                <ul className="">
                    <li className="nav-item active">
                        <button onClick={() => { setFrameLink("/products") }}>Products</button>
                    </li>
                    <li className="nav-item active">
                        <button onClick={() => { setFrameLink("/users") }}>Users</button>
                    </li>
                    <li className="nav-item active">
                        <button onClick={() => { setFrameLink("/orders") }}>Orders</button>
                    </li>
                </ul>
            </nav>
            <iframe className='admin-frame' src={frameLink}></iframe>
        </div>
        <div className='admin-mobile'>
            <h1>Az admin felület mobilon és tableten nem elérhető</h1>
        </div>
    </>
}