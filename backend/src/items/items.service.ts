import { HttpException, Injectable } from '@nestjs/common';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class ItemsService {

  constructor(private readonly db: PrismaService) { }

  async create(createItemDto: CreateItemDto) {

    const activeCommissions = await this.db.commission.findMany({ where: { commissionState: "Active", user_id: createItemDto.commission_user_id } })

    if (activeCommissions.length == 0) {
      //create order and put item inside
      try {
        const newCommission = await this.db.commission.create({
          data: {
            shipping_address: createItemDto.commission_shipping_address,
            user_id: createItemDto.commission_user_id,
            commissionState: "Active",
            extratext: createItemDto.commission_extratext
          }
        });

        console.log("New order created:", newCommission);

        return await this.db.item.create({
          data: {
            commissionId: newCommission.id,
            productId: createItemDto.productId,
            quantity: createItemDto.quantity,
            productName: createItemDto.productName
          }
        });
      } catch (error) {
        console.error("Error creating order:", error);
        throw new HttpException("Failed to create order", 500);
      }

    } else {
      const activeCommission = activeCommissions[0]
      const activeItem = await this.db.item.findFirst({where: {productId: createItemDto.productId, commissionId: activeCommission.id}})
      if (activeItem) {
        return this.db.item.update({
          where: {id: activeItem.id},
          data: {quantity: activeItem.quantity+1}
        })
      } else {
        return this.db.item.create({
          data: {
            commissionId: activeCommission.id,
            productId: createItemDto.productId,
            quantity: createItemDto.quantity,
            productName: createItemDto.productName
          }
        });
      }
    }
  }

  findAll() {
    return this.db.item.findMany();
  }

  findOne(id: number) {
    return this.db.item.findUnique({ where: { id } });
  }

  update(id: number, updateItemDto: UpdateItemDto) {
    return this.db.item.update({
      where: { id },
      data: updateItemDto
    });
  }

  remove(id: number) {
    return this.db.item.delete({
      where: { id }
    });
  }
}
