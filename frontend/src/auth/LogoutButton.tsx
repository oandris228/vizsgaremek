import { useNavigate } from "react-router-dom"

export default function LogoutButton(token: any) {

  async function logoutUser() {

    const tokenBody = {
      token: token.token.token
    }
    console.log(tokenBody)
    return fetch('http://localhost:3000/auth/logout', {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token.token.token
      },
      body: JSON.stringify(tokenBody)
    })
      .then(data => data.json())
      .then(() => {
        localStorage.clear();
        window.location.href = "/login"
      })
  }

  return <button onClick={logoutUser}>Kijelentkezés</button>
}