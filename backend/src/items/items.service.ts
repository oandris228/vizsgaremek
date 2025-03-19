import { HttpException, Injectable } from '@nestjs/common';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class ItemsService {

  constructor(private readonly db: PrismaService) { }

  async create(createItemDto: CreateItemDto) {

    const activeOrders = await this.db.order.findMany({ where: { orderState: "Active" } })

    if (activeOrders.length == 0) {
      //create order and put item inside
      try {
        const newOrder = await this.db.order.create({
          data: {
            shipping_address: createItemDto.order_shipping_address,
            user_id: createItemDto.order_user_id,
            orderState: "Active",
            extratext: createItemDto.order_extratext
          }
        });

        console.log("New order created:", newOrder);

        return await this.db.item.create({
          data: {
            orderId: newOrder.id,
            productId: createItemDto.productId,
            quantity: createItemDto.quantity
          }
        });
      } catch (error) {
        console.error("Error creating order:", error);
        throw new HttpException("Failed to create order", 500);
      }

    } else {
      return this.db.item.create({
        data: {
          orderId: activeOrders[0].id,
          productId: createItemDto.productId,
          quantity: createItemDto.quantity
        }
      })
    }
  }

  findAll() {
    return this.db.item.findMany();
  }

  findByOrder(orderId: number) {
    return this.db.item.findMany({
      where: { orderId }
    });
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
