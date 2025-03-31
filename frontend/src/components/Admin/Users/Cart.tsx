import { useEffect, useState } from "react"
import { Commission } from "../../../types";
import { useNavigate } from "react-router-dom";

export function Cart() {
    const [commission, setCommission] = useState<Commission>();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();


    const fetchCommission = async () => {
        try {
            const response = await fetch("http://localhost:3000/orders/active");
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: Commission = await response.json();

            setCommission(data);
        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(()=> {
        fetchCommission()
    }, [commission])


    return <>
        <h1>Cart details</h1>
        <h3>{commission?.shipping_address}</h3>
        <h3>{commission?.extratext}</h3>
    </>
}

/**
 * <ul>
            {order?.items.map((item) => (
                <li>Termék id: {item.productId} Mennyiség: {item.quantity}</li>
            ))}
        </ul>
 */