import React, { useContext, useEffect, useState } from "react"
import LogoutButton from "../auth/LogoutButton"
import { AuthContext } from "../App"
import { User } from "../types";
import { GetProfile } from "../functions";

export default function Profile() {
    const token = useContext(AuthContext)

    const [user, setUser] = useState<User>();

    useEffect(() => {
        GetProfile(token).then((e)=> setUser(e))
    }, [token])

    return (<>
        <h1>Welcome {user?.username}</h1>
        <h2>email: {user?.email}</h2>
        <h2>shipping_address: {user?.shipping_address}</h2>
        <LogoutButton token={token} />
    </>)
}