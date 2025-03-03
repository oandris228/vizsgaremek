import React, { useContext } from "react"
import LogoutButton from "../auth/LogoutButton"
import { AuthContext } from "../App"

export default function Profile() {
    const token = useContext(AuthContext)
    return( <>
        <h1>Gamer</h1>
        <h2>token: {token}</h2>
        <LogoutButton token={token}/>
    </>)
}