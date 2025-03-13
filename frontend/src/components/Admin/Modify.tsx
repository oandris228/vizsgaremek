import { useEffect, useState, useCallback } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

export default function Modify() {

  const { id } = useParams();

  type FormData = {
    id: number;
    name: string;
    price: number;
    category: "Tea" | "Other";
    tea_type?: string;
    tea_flavor?: string;
    others_description?: string;
    others_img?: string;
  }

  const [formData, setFormData] = useState<FormData>({
    id: 0,
    name: "",
    price: 0,
    category: "Tea",
    tea_flavor: "",
    tea_type: "",
    others_description: "",
    others_img: ""
  });
  const [product, setProduct] = useState<FormData>();
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

        const formattedData: FormData = {
          id: data.id,
          name: data.name,
          price: data.price,
          category: data.category,
          tea_type: data.Tea.length > 0 ? data.Tea[0].type : undefined,
          tea_flavor: data.Tea.length > 0 ? data.Tea[0].flavor : undefined,
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
      console.log("fuc u");
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

  if (loading) return <p>Loading geometry...</p>;
  if (error) return <p>Error fetching data: {error}</p>;

  return <>
    <h1>Módosítás</h1>
    <h3>You can only edit some of the things</h3>
        <form>
            <label>Name:</label>
            <input type="text" name="name" onChange={(e) => { handleChange(e) }} value={formData.name}/><br />

            <label>Price:</label>
            <input type="number" name="price" onChange={(e) => { handleChange(e) }} value={formData.price}/><br />

            <label>Tea Type:</label>
            <input type="text" name="tea_type" onChange={(e) => { handleChange(e) }} value={formData.tea_type}/><br />

            <label>Tea Flavor:</label>
            <input type="text" name="tea_flavor" onChange={(e) => { handleChange(e) }} value={formData.tea_flavor}/><br />

            <label>Other Description:</label>
            <input type="text" name="others_description" onChange={(e) => { handleChange(e) }} value={formData.others_description}/><br />

            <label>Other Image:</label>
            <input type="text" name="others_img" onChange={(e) => { handleChange(e) }} value={formData.others_img}/><br />

            <button onClick={(e) => handleSubmit(e)}>Submit</button>
        </form>
  </>
}