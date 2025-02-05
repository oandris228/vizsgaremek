import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { ProductsService } from './products.service';
import { CreateProductDto } from './dto/create-product.dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { TeaService } from 'src/tea/tea.service';
import { OthersService } from 'src/others/others.service';
import { ApiBody } from '@nestjs/swagger';

@Controller('products')
export class ProductsController {
  constructor(
    private readonly productsService: ProductsService,
    private readonly teaService: TeaService,
    private readonly othersService: OthersService
  ) {}

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
  create(@Body() createProductDto: CreateProductDto) {
    return this.productsService.create(createProductDto);
  }

  @Get()
  findAll() {
    return this.productsService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.productsService.findOne(+id);
  }

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
  update(@Param('id') id: string, @Body() updateProductDto: UpdateProductDto) {
    return this.productsService.update(+id, updateProductDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.productsService.remove(+id);
  }
}
