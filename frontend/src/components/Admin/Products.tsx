import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export function ProductFelvetel() {
    const [products, setProducts] = useState<BaseProduct[]>([]);
    const [teas, setTeas] = useState<BaseProduct[]>([]);
    const [others, setOthers] = useState<BaseProduct[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    type BaseProduct = {
        id: number;
        name: string;
        price: number;
        category: "Tea" | "Other";
        Tea?: Tea[];
        Other?: Other[];
    };

    type Tea = {
        id?: number;
        type?: string;
        flavor?: string;
    };

    type Other = {
        id?: number;
        description?: string;
        img?: string;
    };

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
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error fetching data: {error}</p>;

    return <>
        <h1>Listázás</h1>

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
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>

        <h1>Felvétel</h1>
        <form method="post" action="http://localhost:3000/products" onSubmit={() =>  {navigate('/products')}}>
            <label>ID:</label>
            <input type="number" name="id" /><br />

            <label>Name:</label>
            <input type="text" name="name" /><br />

            <label>Price:</label>
            <input type="number" name="price" /><br />

            <label>Category:</label>
            <input type="text" name="category" /><br />

            <label>Tea Type:</label>
            <input type="text" name="tea_type" /><br />

            <label>Tea Flavor:</label>
            <input type="text" name="tea_flavor" /><br />

            <label>Other Description:</label>
            <input type="text" name="others_description" /><br />

            <label>Other Image:</label>
            <input type="text" name="others_img" /><br />

            <button type="submit">Submit</button>
        </form>
    </>
}