export type User = {
    id: number;
    username: string;
    email: string;
    shipping_address: string;
    role: string;
    commissions: Commission[]
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
    type?: string;
    flavor?: string;
    color?: string;
};

export type Other = {
    description?: string;
    img?: string;
};

export type Commission = {
    id: number;
    user_id: number;
    shipping_address: string;
    extratext: string;
    items: Item[];
    commissionState: string;
}

export type Item = {
    id: number;
    commissionId: number;
    productId: number;
    quantity: number;
    productName: string;
}

export type ProductFormData = {
    name: string;
    price: number;
    category: "Tea" | "Other";
    tea_type?: string;
    tea_flavor?: string;
    tea_color?: string;
    others_description?: string;
    others_img?: string;
}

export type CommissionFormData = {
    //ITEM
    orderId?: number;
    productId?: number;
    quantity?: number;
    productName?: string;
    //ITEM

    commission_shipping_address: string;
    commission_user_id: number;
    commission_commissionState: "Active" | "Processed" | "Completed";
    commission_extratext: string;
}

export type UserFormData = {
    username: string;
    password: string;
    email: string;
    shipping_address: string;
}