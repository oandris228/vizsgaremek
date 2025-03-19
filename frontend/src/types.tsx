export type User = {
    id: number;
    username: string;
    email: string;
    shipping_address: string;
    role: string;
};

export type BaseProduct = {
    id: number;
    name: string;
    price: number;
    category: "Tea" | "Other";
    Tea?: Tea[];
    Other?: Other[];
};

export type Tea = {
    id?: number;
    type?: string;
    flavor?: string;
};

export type Other = {
    id?: number;
    description?: string;
    img?: string;
};

export type ProductFormData = {
    id: number;
    name: string;
    price: number;
    category: "Tea" | "Other";
    tea_type?: string;
    tea_flavor?: string;
    others_description?: string;
    others_img?: string;
}

export type OrderFormData = {
    //ITEM
    orderId?: number;
    productId?: number;
    quantity?: number;
    //ITEM

    order_id: number;
    order_shipping_address: string;
    order_user_id: number;
    order_orderState: "Active" | "Processed" | "Completed";
    order_extratext: string;
}