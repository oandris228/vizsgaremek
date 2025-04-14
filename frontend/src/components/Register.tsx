import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserFormData } from "../types";

export function Regisztracio() {

    const navigate = useNavigate();
    const [formData, setFormData] = useState<UserFormData>({
        username: "",
        email: "",
        password: "",
        shipping_address: "",
    })

    const handleSubmit = async (e: any) => {
        e.preventDefault();
        try {
            await fetch('http://localhost:3000/users', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData),
            });
            navigate('/');
        } catch (error: any) {
            console.error("Error submitting form:", error);
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value.toString(),
        }));
    };

    return <>
        <div className="information-box">
            <h1 className="">Regisztráció</h1>
            <form method="post" action="http://localhost:3000/users">
                <label>Felhasználónév:</label>
                <input type="text" name="username" onChange={(e) => handleChange(e)} /><br />

                <label>E-mail:</label>
                <input type="text" name="email" onChange={(e) => handleChange(e)} /><br />

                <label>Jelszó:</label>
                <input type="password" name="password" onChange={(e) => handleChange(e)} /><br />

                <label>Szállítási cím (Opcionális):</label>
                <input type="text" name="shipping_address" onChange={(e) => handleChange(e)} /><br />

                <button type="submit" className="button-confirm" onClick={(e) => handleSubmit(e)}>Submit</button>
            </form>
        </div>
    </>
}