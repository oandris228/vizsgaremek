import { useNavigate } from "react-router-dom"

export default function LogoutButton(token: any) {
    const navigate = useNavigate();

    async function logoutUser() {
        return fetch('http://localhost:3000/logout', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(token)
        })
          .then(data => data.json())
          .then(() => {
            localStorage.clear();
            navigate('/login');
          })
       }

    return <button onClick={logoutUser}>Kijelentkezés</button>
}