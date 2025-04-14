import { useEffect, useState } from "react"
import { Commission } from "../../../types";
import { useNavigate, useParams } from "react-router-dom";

export function Cart() {
    const { user_id } = useParams();
    const [commission, setCommission] = useState<Commission>();
    const [nocommission, setNoCommission] = useState(true);
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
            if (data[0] == undefined) {
                setNoCommission(true);
            } else {
                setNoCommission(false);
            }
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
                const extra = commission.extratext
                const response = await fetch(`http://localhost:3000/commissions/${commission.id}`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ commissionState: "Processed", extratext: extra }),
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

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        const apaininthe: Commission = {
            id: commission!.id,
            user_id: commission!.user_id,
            shipping_address: commission!.shipping_address,
            extratext: value,
            items: commission!.items,
            commissionState: commission!.commissionState
        }
        setCommission(apaininthe);
    };

    return <>
        <div className="information-box">
            <h1 className="cart-title">Cart details</h1>
            {!nocommission ? (
                <>
                    <ul className="cart-list">
                        {commission?.items?.map((item) => (
                            <li key={item.commissionId} className="cart-item"><p>#{item.productId} {item.productName} Mennyiség: {item.quantity}<span> <button className="button-delete" onClick={() => RemoveItem(item.id)}>Törlés</button></span></p></li>
                        ))}
                    </ul>
                    <div className="order-info">
                        <h3 className="order-stuff">Szállítási cím: <span className="italic">{commission?.shipping_address}</span></h3>
                        <h3 className="order-stuff">Megjegyzés: <input type="text" className="extratext-input" onChange={(e) => { handleChange(e) }} /></h3>
                    </div>
                    <span className="button-container">
                        <button className="button-confirm" onClick={(e) => SumbmitCommission(e)}>Rendelés feladása</button>
                        <button className="button-delete" onClick={(e) => RemoveCommission(e)}>Rendelés törlése</button>
                    </span>
                    <p className="italic">A rendelések törlése visszafordíthatatlan.</p>
                </>
            ) : (<>
                <h3>Üres a kosarad! Adj hozzá egy terméket</h3>
            </>)}
        </div>
    </>
}