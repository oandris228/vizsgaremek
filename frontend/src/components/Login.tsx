import { useState } from 'react';
import { loginUser } from '../functions';
import { useNavigate } from 'react-router-dom';

export default function Login({ setToken }: any) {
  const navigate = useNavigate();
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState("");

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      const token = await loginUser({
        username,
        password
      });
      setToken(token);
      navigate('/profile')
    } catch (error: any) {
      console.log(error.message);
      switch (error.message) {
        case "401": setErrors("Hibás bejelentkezési adatok!"); break;
        default: setErrors("Hiba történt"); break;
      }
    }
  }

  return (
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
      {errors && (
        <div>
          <p className='text-red-600'><strong>Hiba:</strong>{errors}</p>
        </div>
      )}
    </div>
  )
}