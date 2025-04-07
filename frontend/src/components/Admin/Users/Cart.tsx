import { useEffect, useState } from "react"
import { Commission } from "../../../types";
import { useNavigate, useParams } from "react-router-dom";

export function Cart() {
    const { user_id } = useParams();
    const [commission, setCommission] = useState<Commission>();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();


    const fetchCommission = async () => {
        try {
            const response = await fetch(`http://localhost:3000/commissions/active/${user_id}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data: Commission[] = await response.json();

            setCommission(data[0]);
            console.log(data[0])
        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCommission()
    }, [])


    async function RemoveItem(id: number) {
            try {
                const response = await fetch(`http://localhost:3000/items/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                await fetchCommission();
            } catch (err: any) {
                setError(err.message);
            }
    }

    async function RemoveCommission(e: any) {
        e.preventDefault();
        if (commission) {
            try {
                const response = await fetch(`http://localhost:3000/commissions/${commission.id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                navigate('/');
            } catch (err: any) {
                setError(err.message);
            }
        } else {
            throw new Error("no commission to submit")
        }
    }

    async function SumbmitCommission(e: any) {
        e.preventDefault();
        if (commission) {
            try {
                const response = await fetch(`http://localhost:3000/commissions/${commission.id}`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ commissionState: "Processed"}),
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                navigate('/');
            } catch (err: any) {
                setError(err.message);
            }
        } else {
            throw new Error("no commission to submit")
        }
    }

    return <>
        <h1>Cart details</h1>
        <h3>{commission?.shipping_address}</h3>
        <h3>{commission?.extratext}</h3>
        <ul>
            {commission?.items?.map((item) => (
                <li key={item.commissionId}><p>#{item.productId} {item.productName} Mennyiség: {item.quantity} VESSZŐ </p> <button onClick={() => RemoveItem(item.id)}>Törlés</button></li>
            ))}
        </ul>
        <button onClick={(e) => SumbmitCommission(e)}>Rendelés feladása</button><br />
        <button onClick={(e) => RemoveCommission(e)}>Rendelés törlése (ez a döntés visszafordíthatatlan)</button>
    </>
}