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
        <div className="information-box">
            <h1>Üdvözlünk <span className="profile-username">{user?.username}</span></h1>
            <p>Email-cím: <span className="italic m-2">{user?.email}</span></p>
            <p>Szállítási cím: <span className="italic m-2">{user?.shipping_address}</span></p>
            <h3>Rendelések: </h3>
            <ul className="profile-comm">
                {user?.commissions.map((commission) => (
                    <li key={commission.id}>
                        <button className="profile-comm-item">Rendelés ID: {commission.id}; Rendelés státusza: {commission.commissionState}; Megjegyzés: {commission.extratext}</button>
                    </li>
                ))}
            </ul>
        </div>
    </>)
}