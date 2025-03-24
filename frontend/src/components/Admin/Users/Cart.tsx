import { useEffect, useState } from "react"
import { Order } from "../../../types";
import { useNavigate } from "react-router-dom";

export function Cart() {
    const [order, setOrder] = useState<Order>();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();


    const fetchOrder = async () => {
        try {
            const response = await fetch("http://localhost:3000/orders/active");
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: Order = await response.json();

            setOrder(data);
        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(()=> {
        fetchOrder()
    }, [order])


    return <>
        <h1>Cart details</h1>
        <h3>{order?.shipping_address}</h3>
        <h3>{order?.extratext}</h3>
    </>
}

/**
 * <ul>
            {order?.items.map((item) => (
                <li>Termék id: {item.productId} Mennyiség: {item.quantity}</li>
            ))}
        </ul>
 */