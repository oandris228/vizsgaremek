import { Injectable } from '@nestjs/common';
import { CreateOrderDto } from './dto/create-order.dto';
import { UpdateOrderDto } from './dto/update-order.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class OrdersService {

  constructor(private readonly db: PrismaService) {}

  create(createOrderDto: CreateOrderDto) {
    return this.db.order.create({
      data: createOrderDto
    });
  }

  findAll() {
    return this.db.order.findMany();
  }

  findOne(id: number) {
    return this.db.order.findUnique({
      where: {id}
    });
  }

  update(id: number, updateOrderDto: UpdateOrderDto) {
    return this.db.order.update({
      where: {id},
      data: updateOrderDto
    });
  }

  remove(id: number) {
    return this.db.product.delete({
      where: {id}
    });
  }
}
