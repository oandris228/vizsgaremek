import { useContext, useEffect, useState } from "react";
import { BaseProduct, CommissionFormData, User } from "../types";
import { useNavigate } from "react-router-dom";
import { GetProfile } from "../functions";
import { AuthContext } from "../App";

interface ProductProps {
    product: BaseProduct;
}

export const Card: React.FC<ProductProps> = ({ product }) => {
    const [user, setUser] = useState<User>();
    const navigate = useNavigate()
    const token = useContext(AuthContext);


    useEffect(() => {
        GetProfile(token).then((e) => setUser(e))
    })

    async function AddToCart(item_product: BaseProduct): Promise<void> {
        if (!user) {
            navigate('/login')
        } else {
            const formData: CommissionFormData = {
                productId: item_product.id,
                quantity: 1,
                productName: item_product.name,
                commission_shipping_address: user.shipping_address,
                commission_user_id: user.id,
                commission_extratext: "",
                commission_commissionState: "Active"
            }
            try {
                await fetch('http://localhost:3000/items', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData),
                });
                navigate(`/cart/${user.id}`);
            } catch (error: any) {
                console.error("Error submitting form:", error);
            }

        }
    }

    if (product.category == "Tea") {
        return <div className="card">
            <div className="product-header tea-header" style={{
                backgroundColor: product.Tea?.[0].color
            }}>
                <img src="tea.png" alt="tea icon thing" className="tea-image" />
            </div>
            <div className="container">
                <h3>{product.name}</h3>
                <h4>{product.price}</h4>
                <p>{product.Tea?.[0].type}</p>
                <p>{product.Tea?.[0].flavor}</p>
            </div>
            <button onClick={() => AddToCart(product)}>Add to Cart</button>
        </div>
    } else {
        return <div className="card">
            <div className="product-header">
                <img src={product.Other?.[0].img} alt="Poduct image" className="other-img" />
            </div>
            <div className="container">
                <h3>{product.name}</h3>
                <h4>{product.price}</h4>
                <p>{product.Other?.[0].description}</p>
                <p>{product.Other?.[0].img}</p>
            </div>
            <button onClick={() => AddToCart(product)}>Add to Cart</button>
        </div>
    }
}

/**
 * {teas.map((product) => (
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
                            <td><button className="btn btn-danger" onClick={() => { handleDelete(product.id) }}>Törlés</button></td>
                            <td><button className="btn btn-primary" onClick={() => { handleModify(product.id) }}>Módosítás</button></td>
                        </tr>
                    ))}
 */