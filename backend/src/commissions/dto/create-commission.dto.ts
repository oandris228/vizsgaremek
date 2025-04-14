import { ApiProperty } from "@nestjs/swagger";
import { CommissionState } from "@prisma/client";
import { IsEnum, IsInt, IsOptional, IsString } from "class-validator";

export class CreateCommissionDto {
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
    @IsEnum(CommissionState)
    @ApiProperty({
        example: "Active"
    })
    commissionState: CommissionState;

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
