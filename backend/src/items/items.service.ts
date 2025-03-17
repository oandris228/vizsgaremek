import { Injectable } from '@nestjs/common';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class ItemsService {

  constructor(private readonly db: PrismaService) {}

  create(createItemDto: CreateItemDto) {
    return this.db.item.create({
      data: createItemDto
    });
  }

  findAll() {
    return this.db.item.findMany();
  }

  findByOrder(orderId: number) {
    return this.db.item.findMany({
      where: {orderId}
    });
  }

  findOne(id: number) {
    return this.db.item.findUnique({where: {id}});
  }

  update(id: number, updateItemDto: UpdateItemDto) {
    return this.db.item.update({
      where: {id},
      data: updateItemDto
    });
  }

  remove(id: number) {
    return this.db.item.delete({
      where: {id}
    });
  }
}
