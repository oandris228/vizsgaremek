import { User } from "./types";

export async function GetProfile(token: string) {

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