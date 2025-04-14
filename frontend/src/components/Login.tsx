import { useState } from 'react';
import { loginUser } from '../functions';
import { useNavigate } from 'react-router-dom';

export default function Login({ setToken }: any) {
  const navigate = useNavigate();
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    const token = await loginUser({
      username,
      password
    });
    setToken(token);
    navigate('/profile')
  }

  return(
    <div className="information-box">
      <h1 className='p-3'>Bejelentkezés</h1>
      <form onSubmit={handleSubmit}>
        <label>
          <p>Felhasználónév</p>
        </label>
        <input type="text" onChange={e => setUserName(e.target.value)} />
        <label>
          <p>Jelszó</p>
        </label>
        <input type="password" onChange={e => setPassword(e.target.value)} />
        <div>
          <button type="submit" className='button-confirm'>Bejelentkezés</button>
        </div>
      </form>
    </div>
  )
}