import { ApiProperty } from "@nestjs/swagger";
import { IsInt, IsString } from "class-validator";

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
     * fill with a string of the ID-s of the ordered products separated by a ;
     */
    @IsString()
    @ApiProperty({
        example: "1001;1102;1010"
    })
    cart: string;

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
}
