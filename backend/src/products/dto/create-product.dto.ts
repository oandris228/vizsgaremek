export class CreateProductDto {
    id: number;
    name: string;
    price: number;
    category: "Tea" | "Other";
    tea_id?: string;
    other_id?: string;

    tea_name?: string;
    tea_type?: string;
    tea_flavor?: string;

    others_description?: string;
    others_img?: string
}
