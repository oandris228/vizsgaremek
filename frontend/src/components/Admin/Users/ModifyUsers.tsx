import { useEffect, useState, useCallback } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { User } from '../../../types';

export default function ModifyUsers() {

  const { id } = useParams();

  const [formData, setFormData] = useState<User>({
    id: 0,
    username: ' ',
    email: ' ',
    shipping_address: ' ',
    role: 'User',
    commissions: []
  });
  const [product, setProduct] = useState<User>();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async (id: number) => {
      try {
        const response = await fetch(`http://localhost:3000/users/${id}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const formattedData: User = {
          id: data.id,
          username: data.username,
          email: data.email,
          shipping_address: data.shipping_address,
          role: data.role,
          commissions: data.commissions
        };
        console.log(formattedData)

        setFormData(formattedData);
      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    if (id == undefined) {
      console.log("fuc u");
    } else {
      fetchProduct(+id);
    }
  }, []);

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:3000/users/${id}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      navigate('/users');
    } catch (err: any) {
      setError(err.message);
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value.toString(),
    }));
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error fetching data: {error}</p>;

  return <>
    <h1>Módosítás</h1>
    <h3>You can only edit some of the things</h3>
    <form>
      <label>Username:</label>
      <input
        type="text"
        name="username"
        onChange={(e) => handleChange(e)}
        value={formData.username}
      /><br />

      <label>Email:</label>
      <input
        type="email"
        name="email"
        onChange={(e) => handleChange(e)}
        value={formData.email}
      /><br />

      <label>Shipping Address:</label>
      <input
        type="text"
        name="shipping_address"
        onChange={(e) => handleChange(e)}
        value={formData.shipping_address}
      /><br />

      <label>Role:</label>
      <input
        type="text"
        name="role"
        onChange={(e) => handleChange(e)}
        value={formData.role}
      /><br />

      <button onClick={(e) => handleSubmit(e)}>Submit</button>
    </form>

  </>
}