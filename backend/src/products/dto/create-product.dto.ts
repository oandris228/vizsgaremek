import { ApiProperty } from "@nestjs/swagger";
import { Contains, IsIn, IsInt, IsOptional, IsString, Min } from "class-validator";
const categories = ["Tea", "Other"];

export class CreateProductDto {
    /**
     * the name of the product
     */
    @IsString()
    @ApiProperty({
        example: "Basic Tea"
    })
    name: string;

    /**
     * the price of the tea
     */
    @IsInt()
    @Min(1000)
    @ApiProperty({
        example: 1500
    })
    price: number;

    /**
     * the category of the product
     * 
     * HAS TO BE EITHER "Tea" OR "Other" TO WORK
     */
    @IsString()
    @IsIn(categories)
    @ApiProperty({
        example: "Tea"
    })
    category: "Tea" | "Other";

    /**
     * IGNORE THIS, it's autoincremented when creating the product
     */
    @IsOptional()
    @IsInt()
    @ApiProperty({
        example: 10
    })
    tea_id?: number;

    /**
     * IGNORE THIS, it's autoincremented when creating the product
     */
    @IsOptional()
    @IsInt()
    @ApiProperty({
        example: 10
    })
    other_id?: number;

    /**
     * ONLY FILL THIS WHEN CREATING A TEA, OTHERWISE IT IS IGNORED
     * 
     * the type of tea (filtered/mix/grinded, etc..)
     */
    @IsOptional()
    @IsString()
    @ApiProperty({
        example: "grinded"
    })
    tea_type?: string;

    /**
     * ONLY FILL THIS WHEN CREATING A TEA, OTHERWISE IT IS IGNORED
     * 
     * the flavor of the tea
     */
    @IsOptional()
    @IsString()
    @ApiProperty({
        example: "strawberry"
    })
    tea_flavor?: string;

    tea_color?: string;

    /**
     * ONLY FILL THIS WHEN CREATING AN 'OTHER', OTHERWISE IT IS IGNORED
     * 
     * the description of the product
     */
    @IsOptional()
    @IsString()
    @ApiProperty({
        example: "here are the details of the product, very interesting"
    })
    others_description?: string;

    /**
     * ONLY FILL THIS WHEN CREATING AN 'OTHER', OTHERWISE IT IS IGNORED
     * 
     * the image path for the product, only accepts '.png' format
     */
    @IsOptional()
    @IsString()
    @Contains(".png")
    @ApiProperty({
        example: "imgpath.png"
    })
    others_img?: string
}
