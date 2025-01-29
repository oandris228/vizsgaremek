import { Injectable } from '@nestjs/common';
import { CreateProductDto } from './dto/create-product.dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { PrismaService } from 'src/prisma.service';
import { TeaService } from 'src/tea/tea.service';

@Injectable()
export class ProductsService {

  constructor(
    private readonly db: PrismaService,
    private readonly teaservice: TeaService
  ) {}

  async create(createProductDto: CreateProductDto) {

    //Create product

    const temp_product = await this.db.product.create({
      data: {
        id: createProductDto.id,
        name: createProductDto.name,
        price: createProductDto.price,
        category: createProductDto.category
      }
    })

    if(createProductDto.category == "Tea") {
      //Create Tea and store it
      const tea_to_connect = await this.db.tea.create({
        data: {
          type: createProductDto.tea_type,
          flavor: createProductDto.tea_flavor,
          productId: temp_product.id
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
      //Create Other and store it
      let other_to_connect = await this.db.other.create({
        data: {
          description: createProductDto.others_description,
          img: createProductDto.others_img,
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

    console.log(updateProductDto)

    this.db.product.update({
      where: {id},
      data: {
        name: updateProductDto.name,
        price: updateProductDto.price
      }
    })

    if (updateProductDto.category == "Other") {
      console.log("uh oh")
      this.db.other.update({
        where: {id: updateProductDto.other_id},
        data: {
          description: updateProductDto.others_description,
          img: updateProductDto.others_img
        }
      })
    } else {
      console.log("Uh Oh")
      const dtobj = {
        type: updateProductDto.tea_type,
        flavor: updateProductDto.tea_flavor,
        productId: id
      }
      this.teaservice.update(updateProductDto.tea_id, dtobj)
    }
  }

  async remove(id: number) {

    const thing_to_remove = await this.db.product.findUnique({ where: {id}})

    this.db.product.delete({
      where: {id}
    });

    if (thing_to_remove.category == "Tea") {
      this.db.tea.delete({
        where: {productId: id}
      })
    } else {
      this.db.other.delete({
        where: {productId: id}
      })
    }
  }
}