export class CreateItemDto {
    orderId: number; // never used, send wtvr
    productId: number;
    quantity: number;
    //order props if u create an order
    order_id?: number;
    order_shipping_address?: string;
    order_user_id?: number;
    order_orderState?: "Active" | "Processed" | "Completed";
    order_extratext?: string;
}
