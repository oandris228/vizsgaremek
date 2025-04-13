import React, { useContext, useEffect, useState } from "react"
import { AuthContext } from "../App"
import { User } from "../types";
import { GetProfile } from "../functions";
import { useNavigate } from "react-router-dom";

export default function Profile() {
    const token = useContext(AuthContext)
    const navigate = useNavigate();

    const [user, setUser] = useState<User>();

    useEffect(() => {
        GetProfile(token).then((e) => setUser(e))
    }, [token])

    return (<>
        <div className="profile-div">
            <h1>Üdvözlünk <span className="profile-username">{user?.username}</span></h1>
            <h2>email: {user?.email}</h2>
            <h2>shipping_address: {user?.shipping_address}</h2>
            <ul>
                {user?.commissions.map((commission) => (
                    <li key={commission.id}>
                        <button style={{ "padding": 10 }}>Rendelés ID: {commission.id}; Rendelés státusza: {commission.commissionState}</button>
                    </li>
                ))}
            </ul>
        </div>
    </>)
}