import { createContext, useEffect, useState } from "react";
import { User } from "../types"
import { useNavigate } from "react-router-dom";

type AuthContextType = {
    user: User | null;
    register: (userdata: User, password: string) => void;
    login: (username: string, password: string) => void;
    logout: () => void;
    getprofile: () => void;
};

type Props = { children: React.ReactNode };

export const AuthContext = createContext<AuthContextType>({} as AuthContextType);

export const AuthProvider = ({ children }: Props) => {
    const navigate = useNavigate();
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        const load = async () => {
            getprofile();
        }
        if (token) {
            setToken(token);
            load();
        }
    }, [token])

    const register = async (userdata: User, password: string) => {
        const response = await fetch('http://localhost:3000/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userdata),
        });
        if (!response.ok) {
            throw new Error(`${response.status}`)
        }
        login(userdata.username, password);
    }

    const login = async (username: string, password: string) => {
        const response = await fetch('http://localhost:3000/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });
        if (!response.ok) {
            throw new Error(`${response.status}`);
        }
        const data = await response.json();
        localStorage.setItem("token", data.access_token);
        setToken(data.access_token);
        navigate('/');
    }

    const getprofile = async () => {
        const response = await fetch('http://localhost:3000/auth', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
        if (!response.ok) {
            throw new Error(`${response.status}`);
        }
        const data: User = await response.json();
        setUser(data);
        return data;
    }

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
        setUser(null);
        navigate('/login')
    }

    return (
        <AuthContext.Provider value={{user, register, login, logout, getprofile}}>
            {children}
        </AuthContext.Provider>
    )

}

