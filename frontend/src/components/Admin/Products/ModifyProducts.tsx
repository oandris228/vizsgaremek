import { useEffect, useState, useCallback } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { ProductFormData } from '../../../types';

export default function ModifyProducts() {

  const { id } = useParams();
  const [formData, setFormData] = useState<ProductFormData>({
    name: "",
    price: 0,
    category: "Tea",
    tea_flavor: "",
    tea_type: "",
    tea_color: "",
    others_description: "",
    others_img: ""
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async (id: number) => {
      try {
        const response = await fetch(`http://localhost:3000/products/${id}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const formattedData: ProductFormData = {
          name: data.name,
          price: data.price,
          category: data.category,
          tea_type: data.Tea.length > 0 ? data.Tea[0].type : undefined,
          tea_flavor: data.Tea.length > 0 ? data.Tea[0].flavor : undefined,
          tea_color: data.Tea.length > 0 ? data.Tea[0].color : undefined,
          others_description: data.Other.length > 0 ? data.Other[0].description : undefined,
          others_img: data.Other.length > 0 ? data.Other[0].img : undefined,
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
      window.alert("fuc u");
    } else {
      fetchProduct(+id);
    }
  }, []);

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:3000/products/${id}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      navigate('/products');
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

  return <div className='information-box'>
    <h1>Módosítás</h1>
    <h3>You can only edit some of the things</h3>
    <form>
      <label>Name:</label>
      <input type="text" name="name" onChange={(e) => { handleChange(e) }} value={formData.name} /><br />

      <label>Price:</label>
      <input type="number" name="price" onChange={(e) => { handleChange(e) }} value={formData.price} /><br />

      <label>Tea Type:</label>
      <input type="text" name="tea_type" onChange={(e) => { handleChange(e) }} value={formData.tea_type} /><br />

      <label>Tea Flavor:</label>
      <input type="text" name="tea_flavor" onChange={(e) => { handleChange(e) }} value={formData.tea_flavor} /><br />

      <label>Tea Color:</label>
      <input type="text" name="tea_color" onChange={(e) => { handleChange(e) }} value={formData.tea_color} /><br />

      <label>Other Description:</label>
      <input type="text" name="others_description" onChange={(e) => { handleChange(e) }} value={formData.others_description} /><br />

      <label>Other Image:</label>
      <input type="text" name="others_img" onChange={(e) => { handleChange(e) }} value={formData.others_img} /><br />

      <button onClick={(e) => handleSubmit(e)}>Submit</button>
    </form>
  </div>
}