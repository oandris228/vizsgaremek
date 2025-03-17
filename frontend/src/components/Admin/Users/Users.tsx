import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Users() {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    type User = {
        id: number;
        username: string;
        email: string;
        shipping_address: string;
        role: string;
    };

    const fetchUsers = async () => {
        try {
            const response = await fetch("http://localhost:3000/users");
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: User[] = await response.json();

            setUsers(data);
        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id: number) => {
        try {
            const response = await fetch(`http://localhost:3000/users/${id}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                setUsers(prevUsers => prevUsers.filter(user => user.id !== id));
                console.log('deleted successfully');
            } else {
                console.error('Failed to delete');
            }
        } catch (error) {
            console.error('Error deleting:', error);
        }
    };

    const handleModify = (id: number) => {
        navigate(`/users/edit/${id}`);
    };

    useEffect(() => {
        fetchUsers();
    }, [users]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;

    //ehhez nincs felvétel, ha fel akarsz venni valakit akkor kérd meg hogy regisztráljon lol

    return (
        <div className="container mt-4">
            <h1>Listázás</h1>
            <table className="table table-striped table-bordered">
                <thead className="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Shipping address</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.email}</td>
                            <td>{user.shipping_address}</td>
                            <td>{user.role}</td>
                            <td><button className="btn btn-danger" onClick={() => { handleDelete(user.id) }}>Törlés</button></td>
                            <td><button className="btn btn-primary" onClick={() => { handleModify(user.id) }}>Módosítás</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}