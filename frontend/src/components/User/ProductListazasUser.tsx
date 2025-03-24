import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { BaseProduct, OrderFormData, User } from "../../types";
import { AuthContext } from "../../App";
import { GetProfile } from "../../functions";

export default function Listazas() {
    const [products, setProducts] = useState<BaseProduct[]>([]);
    const [teas, setTeas] = useState<BaseProduct[]>([]);
    const [others, setOthers] = useState<BaseProduct[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [user, setUser] = useState<User>();
    const token = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
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

        fetchProducts();
        GetProfile(token).then((e)=> setUser(e))
    }, [token]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;

    async function AddToCart(id: number): Promise<void> {
        if (!user) {
            navigate('/login')
        } else {
            const formData: OrderFormData = {
                productId: id,
                quantity: 1,
                order_shipping_address: user.shipping_address,
                order_user_id: user.id,
                order_extratext: "",
                order_orderState: "Active"
            }
            try {
                await fetch('http://localhost:3000/items', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData),
                });
                navigate('/cart');
            } catch (error: any) {
                console.error("Error submitting form:", error);
            }

        }
    }

    return (
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
                    {teas.map((product) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.Tea?.[0]?.type || "N/A"}</td>
                            <td>{product.Tea?.[0]?.flavor || "N/A"}</td>
                            <td>N/A</td>
                            <td>N/A</td>
                            <td><button onClick={() => AddToCart(product.id)}>Add to Cart</button></td>
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
                            <td><button onClick={() => AddToCart(product.id)}>Add to Cart</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}