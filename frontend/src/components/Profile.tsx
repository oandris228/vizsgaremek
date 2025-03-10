import React, { useContext, useEffect, useState } from "react"
import LogoutButton from "../auth/LogoutButton"
import { AuthContext } from "../App"

export default function Profile() {
    const token = useContext(AuthContext)

    const [user, setUser] = useState<User>();

    type User = {
        id: number;
        username: string;
        email: string;
        shipping_address: string;
    };

    useEffect(() => {
        async function GetProfile() {

            const response = await fetch('http://localhost:3000/users/token', {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: User = await response.json();
            setUser(data);
        }
        GetProfile();
    }, [token])

    return (<>
        <h1>Welcome {user?.username}</h1>
        <h2>email: {user?.email}</h2>
        <h2>shipping_address: {user?.shipping_address}</h2>
        <LogoutButton token={token} />
    </>)
}