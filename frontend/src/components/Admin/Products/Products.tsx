import { MouseEvent, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { BaseProduct, ProductFormData } from "../../../types";

export function Products() {
    const [products, setProducts] = useState<BaseProduct[]>([]);
    const [teas, setTeas] = useState<BaseProduct[]>([]);
    const [others, setOthers] = useState<BaseProduct[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [formData, setFormData] = useState<ProductFormData>({
        name: "undefined",
        price: 0,
        category: "Tea",
        tea_flavor: "",
        tea_type: ""
    });
    const navigate = useNavigate();

    const fetchProducts = async () => {
        try {
            const response = await fetch("http://localhost:3000/products");
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: BaseProduct[] = await response.json();

            setProducts(data);

            const teaProducts = data.filter(product => product.category === "Tea");
            const otherProducts = data.filter(product => product.category === "Other");

            setTeas(teaProducts);
            setOthers(otherProducts);

        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, [products]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value.toString(),
        }));
    };

    const handleSubmit = async (e: MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        try {
            await fetch('http://localhost:3000/products', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData),
            });
            fetchProducts()
            navigate('/products');
        } catch (error: any) {
            console.error("Error submitting form:", error);
        }
    };

    const handleDelete = async (productId: number) => {
        try {
            const response = await fetch(`http://localhost:3000/products/${productId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                setProducts(prevProducts => prevProducts.filter(product => product.id !== productId));
                console.log('deleted successfully');
            } else {
                console.error('Failed to delete');
            }
        } catch (error) {
            console.error('Error deleting:', error);
        }
    };

    const handleModify = (id: number) => {
        navigate(`/products/edit/${id}`);
    };

    return <>
        <div className="container">
            <h1>Listázás</h1>
            <table className="table">
                <thead className="thead">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Tea Type</th>
                        <th>Tea Flavor</th>
                        <th>Tea Color</th>
                        <th>Other Description</th>
                        <th>Other Image</th>
                    </tr>
                </thead>
                <tbody>
                    {teas.map((product) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.Tea?.[0]?.type || "N/A"}</td>
                            <td>{product.Tea?.[0]?.flavor || "N/A"}</td>
                            <td>{product.Tea?.[0]?.color || "N/A"}</td>
                            <td>N/A</td>
                            <td>N/A</td>
                            <td><button className="button-delete" onClick={() => { handleDelete(product.id) }}>Törlés</button></td>
                            <td><button className="button-modify" onClick={() => { handleModify(product.id) }}>Módosítás</button></td>
                        </tr>
                    ))}
                    {others.map((product) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>N/A</td>
                            <td>N/A</td>
                            <td>{product.Other?.[0]?.description || "N/A"}</td>
                            <td>
                                {product.Other?.[0]?.img ? (
                                    <img src={product.Other[0].img} alt="Product" width="50" />
                                ) : "N/A"}
                            </td>
                            <td><button className="button-delete" onClick={() => { handleDelete(product.id) }}>Törlés</button></td>
                            <td><button className="button-modify" onClick={() => { handleModify(product.id) }}>Módosítás</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>

        <h1>Felvétel</h1>
        <form>
            <label>Name:</label>
            <input type="text" name="name" onChange={(e) => { handleChange(e) }} /><br />

            <label>Price:</label>
            <input type="number" name="price" onChange={(e) => { handleChange(e) }} /><br />

            <label>Category:</label>
            <input type="text" name="category" onChange={(e) => { handleChange(e) }} /><br />

            <label>Tea Type:</label>
            <input type="text" name="tea_type" onChange={(e) => { handleChange(e) }} /><br />

            <label>Tea Flavor:</label>
            <input type="text" name="tea_flavor" onChange={(e) => { handleChange(e) }} /><br />

            <label>Tea Color:</label>
            <input type="text" name="tea_color" onChange={(e) => { handleChange(e) }} /><br />

            <label>Other Description:</label>
            <input type="text" name="others_description" onChange={(e) => { handleChange(e) }} /><br />

            <label>Other Image:</label>
            <input type="text" name="others_img" onChange={(e) => { handleChange(e) }} /><br />

            <button onClick={(e) => handleSubmit(e)}>Submit</button>
        </form>
    </>
}