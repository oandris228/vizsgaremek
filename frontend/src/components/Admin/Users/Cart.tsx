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
            const response = await fetch("http://localhost:3000/commissions/active");
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


    function RemoveItem(id: number): void {
        throw new Error("Function not implemented.");
    }

    function RemoveCommission(): void {
        throw new Error("Function not implemented.");
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
                <li key={item.commissionId}><p>Termék id: {item.productId} Mennyiség: {item.quantity} VESSZŐ </p> <button onClick={() => RemoveItem(item.id)}>Törlés</button></li>
            ))}
        </ul>
        <button onClick={(e) => SumbmitCommission(e)}>Rendelés feladása</button><br />
        <button onClick={() => RemoveCommission()}>Rendelés törlése</button>
    </>
}