import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { ProductsService } from './products.service';
import { CreateProductDto } from './dto/create-product.dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { TeaService } from 'src/tea/tea.service';
import { OthersService } from 'src/others/others.service';
import { ApiBadRequestResponse, ApiBody, ApiParam, ApiResponse } from '@nestjs/swagger';

@Controller('products')
export class ProductsController {
  constructor(
    private readonly productsService: ProductsService,
    private readonly teaService: TeaService,
    private readonly othersService: OthersService
  ) {}

  /**
   * Creates a new product. (tea/other)
   * @param createProductDto the data of the product
   * @returns json of the created product (does not return the json of the tea/other, only the IDs)
   */
  @Post()
  @ApiBody({
    type: CreateProductDto,
    examples: {
      example1: {
        summary: "example of a tea",
        value: {
          id: 0,
          name: "tea 1",
          price: 1000,
          category: "Tea",
          //below are tea details
          tea_type: "filteres",
          tea_flavor: "strawberry"
        }
      },
      example2: {
        summary: "example of an Other",
        value: {
          id: 0,
          name: "other 1",
          price: 1000,
          category: "Other",
          //below are tea details
          others_description: "description",
          others_img: "img.png"
        }
      }
    }
  })
  @ApiResponse({ status: 201, description: 'A new product was created' })
  @ApiBadRequestResponse({ description: 'The supplied data was invalid' })
  create(@Body() createProductDto: CreateProductDto) {
    return this.productsService.create(createProductDto);
  }

  /**
   * returns all products
   */
  @Get()
  @ApiResponse({status: 200, description: 'The data was successfully returned'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  findAll() {
    return this.productsService.findAll();
  }

  /**
   * returns specific product
   * @param id the ID of the product
   * @returns json of the product
   */
  @Get(':id')
  @ApiParam({
    name: 'id',
    description: 'The unique ID of the order'
  })
  @ApiResponse({status: 200, description: 'The data was successfully returned'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  findOne(@Param('id') id: string) {
    return this.productsService.findOne(+id);
  }

  /**
   * Updates a specific product
   * @param id the ID of the product
   * @param updateProductDto the new data that replaces the old
   * @returns json
   */
  @Patch(':id')
  @ApiBody({
    type: CreateProductDto,
    examples: {
      example1: {
        summary: "example of a tea",
        value: {
          name: "tea 1",
          price: 1000,
          category: "Tea",
          //below are tea details
          tea_id: 0, //THIS IS THE REQUIRED IF YOU WANT TO UPDATE TEA DETAILS
          tea_type: "filteres",
          tea_flavor: "strawberry"
        }
      },
      example2: {
        summary: "example of an Other",
        value: {
          name: "other 1",
          price: 1000,
          category: "Other",
          //below are tea details
          others_id: 0, //THIS IS THE REQUIRED IF YOU WANT TO UPDATE OTHERS DETAILS
          others_description: "description",
          others_img: "img.png"
        }
      }
    }
  })
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the order'
  })
  @ApiResponse({status: 200, description: 'The data was successfully updated'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  update(@Param('id') id: string, @Body() updateProductDto: UpdateProductDto) {
    return this.productsService.update(+id, updateProductDto);
  }

  /**
   * Deletes a specific product.
   * @param id the ID of the product
   * @returns json
   */
  @Delete(':id')
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the product'
  })
  @ApiResponse({status: 200, description: 'The data was successfully deleted'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  remove(@Param('id') id: string) {
    return this.productsService.remove(+id);
  }
}
