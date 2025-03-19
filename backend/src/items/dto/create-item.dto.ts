export class CreateItemDto {
    productId: number;
    quantity: number;
    //order props if u create an order
    order_shipping_address?: string;
    order_user_id?: number;
    order_orderState?: "Active" | "Processed" | "Completed";
    order_extratext?: string;
}
