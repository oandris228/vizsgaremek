import { useContext, useEffect, useState } from "react";
import { BaseProduct, CommissionFormData, User } from "../types";
import { useNavigate } from "react-router-dom";
import { GetProfile } from "../functions";
import { AuthContext } from "../App";

interface ProductProps {
    product: BaseProduct;
}

export const Card:  React.FC<ProductProps> = ({ product }) => {
    const [user, setUser] = useState<User>();
    const navigate = useNavigate()
    const token = useContext(AuthContext);


    useEffect(() => {
        GetProfile(token).then((e) => setUser(e))
    })

    async function AddToCart(id: number): Promise<void> {
        if (!user) {
            navigate('/login')
        } else {
            const formData: CommissionFormData = {
                productId: id,
                quantity: 1,
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
                navigate('/cart');
            } catch (error: any) {
                console.error("Error submitting form:", error);
            }

        }
    }

    if (product.category == "Tea") {
        return <div className="card">
            <div className="teaicon">
                {/**there will be an image here someday */}
            </div>
            <div className="container">
                <h3>{product.name}</h3>
                <h4>{product.price}</h4>
                <p>{product.Tea?.[0].type}</p>
                <p>{product.Tea?.[0].flavor}</p>
            </div>
            <button onClick={() => AddToCart(product.id)}>Add to Cart</button>
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