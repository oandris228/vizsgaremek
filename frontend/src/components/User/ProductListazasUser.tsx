import { useEffect, useState, useCallback } from 'react'
import { useNavigate } from 'react-router-dom';
export default function Listazas() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch("http://localhost:3000/products");
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                console.log(data)
                setProducts(data);
            } catch (err: any) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
        fetchProducts();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;




    return <>
        <div className="container mt-4">
            <h1>Listázás</h1>
            <table className="table table-striped table-bordered">
                <thead className="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Tea Type</th>
                        <th>Tea Flavor</th>
                        <th>Other Description</th>
                        <th>Other Image</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product: any) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.Tea[0].type || "N/A"}</td>
                            <td>{product.Tea[0].flavor || "N/A"}</td>
                            <td>{product.Other[0].description || "N/A"}</td>
                            <td>
                                {product.Other[0].img ? (
                                    <img src={product.others_img} alt={product.name} width="50" />
                                ) : (
                                    "N/A"
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    </>
}