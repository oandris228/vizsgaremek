import React from "react"
import LogoutButton from "../auth/LogoutButton"

export default function Profile(token: any) {
    return( <>
        <h1>Gamer</h1>
        <LogoutButton token={token}/>
    </>)
}