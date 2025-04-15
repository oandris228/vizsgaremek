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
        <div className="card-list">
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
        </div>
    );
}