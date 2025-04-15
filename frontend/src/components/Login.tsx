import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../auth/AuthContext';

export default function Login() {
  const navigate = useNavigate();
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState("");
  const { login } = useContext(AuthContext);


  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      login(username, password)
    } catch (error: any) {
      switch (error.message) {
        case "401":
          setErrors("Hibás bejelentkezési adatok")
          break;
      
        default:
          setErrors("Hiba történt")
          break;
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
          <p className='text-red-600'><strong>{errors}</strong></p>
        </div>
      )}
    </div>
  )
}