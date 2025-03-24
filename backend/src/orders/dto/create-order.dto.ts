import { ApiProperty } from "@nestjs/swagger";
import { OrderState } from "@prisma/client";
import { IsEnum, IsInt, IsOptional, IsString } from "class-validator";

export class CreateOrderDto {
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
    @IsEnum(OrderState)
    @ApiProperty({
        example: "Active"
    })
    orderState: OrderState;

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
