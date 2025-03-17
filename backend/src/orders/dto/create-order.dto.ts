import { ApiProperty } from "@nestjs/swagger";
import { IsInt, IsOptional, IsString } from "class-validator";

export class CreateOrderDto {
    /**
     * the ID of the order
     * 
     * have to input manually
     */
    @IsInt()
    @ApiProperty({
        example: 10323264
    })
    id: number;

    /**
     * the shipping address
     */
    @IsString()
    @ApiProperty({
        example: "1133 Város Utca házszám"
    })
    shipping_address: string;

    /**
     * the ID of the user ordering
     */
    @IsInt()
    @ApiProperty({
        example: 10
    })
    user_id: number;

    /**
     * order state
     * always keep "Active" on creation
     */
    @IsString()
    @ApiProperty({
        example: "Active"
    })
    orderState: "Active" | "Processed" | "Completed";

    /**
     * extra text if the user wants to specify something that we just NEED to know
     */
    @IsString()
    @IsOptional()
    @ApiProperty({
        example: "I am clinically insane."
    })
    extratext?: string;
}
