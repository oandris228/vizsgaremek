import { useContext, useEffect, useState } from "react";
import { BaseProduct, CommissionFormData, User } from "../types";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../auth/AuthContext";

interface ProductProps {
    product: BaseProduct;
}

export const Card: React.FC<ProductProps> = ({ product }) => {
    const navigate = useNavigate()
    const {user} = useContext(AuthContext);

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
                throw new Error("Error submitting form:" + error.message)
            }

        }
    }

    if (product.category == "Tea") {
        return <div className="card">
            <div className="product-header tea-header" style={{
                backgroundColor: product.Tea?.[0].color
            }}>
                <img src="tea.png" alt="tea icon thing" className="product-image" />
            </div>
            <div className="card-container">
                <h3>{product.name}</h3>
                <div className="card-content">
                    <div>
                        <h4>{product.price} Ft</h4>
                        <p>{product.Tea?.[0].type}</p>
                        <p>{product.Tea?.[0].flavor}</p>
                    </div>
                    <div><button onClick={() => AddToCart(product)}>Add to Cart</button></div>
                </div>
            </div>
        </div>
    } else {
        return <div className="card">
            <div className="product-header">
                <img src={product.Other?.[0].img} alt="Poduct image" className="product-image" />
            </div>
            <div className="container">
                <h3>{product.name}</h3>
                <div className="card-content">
                    <div>
                        <h4>{product.price}Ft</h4>
                        <p>{product.Other?.[0].description}</p>
                        <p>{product.Other?.[0].img}</p>
                    </div>
                    <div><button onClick={() => AddToCart(product)}>Add to Cart</button></div>
                </div>
            </div>
        </div>
    }
}