export class CreateItemDto {
    productId: number;
    quantity: number;
    productName: string;
    //order props if u create an order
    commission_shipping_address?: string;
    commission_user_id?: number;
    commission_commissionState?: "Active" | "Processed" | "Completed";
    commission_extratext?: string;
}
