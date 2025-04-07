import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { BaseProduct, CommissionFormData, User } from "../../types";
import { AuthContext } from "../../App";
import { GetProfile } from "../../functions";
import { Card } from "../Card";

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
        GetProfile(token).then((e) => setUser(e))
    }, [token]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;

    return (
        <div className="container mt-4">
            {teas.map((product) => (
                <div key={product.id}>
                    <Card product={product}/>
                </div>
            ))}
            {others.map((product) => (
                <div key={product.id}>
                    <Card product={product}/>
                </div>
            ))}
            {/*<table className="table table-striped table-bordered">
                <thead className="thead-dark">
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
                            <td>{product.Tea?.[0].color || "N/A"}</td>
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
            </table>*/}
        </div>
    );
}