import { Injectable } from '@nestjs/common';
import { CreateProductDto } from './dto/create-product.dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { PrismaService } from 'src/prisma.service';
import { connect } from 'http2';

@Injectable()
export class ProductsService {

  constructor(private readonly db: PrismaService) {}

  async create(createProductDto: CreateProductDto) {

    //Create product

    this.db.product.create({
      data: {
        id: createProductDto.id,
        name: createProductDto.name,
        price: createProductDto.price,
        category: createProductDto.category
      }
    })

    if(createProductDto.category == "Tea") {
      //Create Tea and connect it to Product
      this.db.tea.create({
        data: {
          type: createProductDto.tea_type,
          flavor: createProductDto.tea_flavor,
          productId: createProductDto.id
        }
      })
      //Find the tea you just created and store it
      let tea_to_connect = await this.db.tea.findUnique({
        where: {
          productId: createProductDto.id
        }
      })

      //Update product with Tea
      return this.db.product.update({
        where: {id: createProductDto.id},
        data: {
          tea_id: tea_to_connect.id
        }
      })

    }else {
      //Create Other and connect it to Product
      this.db.other.create({
        data: {
          description: createProductDto.others_description,
          img: createProductDto.others_img,
          productId: createProductDto.id
        }
      })
      //Find the other you just created and store it
      let other_to_connect = await this.db.other.findUnique({
        where: {
          productId: createProductDto.id
        }
      })

      //Update product with Other
      return this.db.product.update({
        where: {id: createProductDto.id},
        data: {
          other_id: other_to_connect.id
        }
      })
    }
  }

  findAll() {
    return this.db.product.findMany();
  }

  findOne(id: number) {
    return this.db.product.findUnique({
      where: {id}
    });
  }

  update(id: number, updateProductDto: UpdateProductDto) {
    return `no.`;
  }

  remove(id: number) {
    return `This action removes a #${id} product`;
  }
}
