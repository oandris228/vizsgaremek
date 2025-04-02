import { useContext } from "react";
import { User } from "./types";
import { useNavigate } from "react-router-dom";

export async function GetProfile(token: string) {
    if (token) {
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
        return data;
    }
}

export async function loginUser(credentials: any) {
    return fetch('http://localhost:3000/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}

export function LogoutUser(token: string) {
    console.log(token);
    if (token) {
        return fetch('http://localhost:3000/auth/logout', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        })
            .then(data => data.json())
            .then(() => {
                localStorage.clear();
            })
    } else {
        console.log('no token')
    }
}