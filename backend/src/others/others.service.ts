import { Injectable } from '@nestjs/common';
import { CreateOtherDto } from './dto/create-other.dto';
import { UpdateOtherDto } from './dto/update-other.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class OthersService {

  constructor(private readonly db: PrismaService) {}

  create(createOtherDto: CreateOtherDto) {
    return this.db.other.create({
      data: createOtherDto
    });
  }

  findAll() {
    return this.db.other.findMany();
  }

  findOne(id: number) {
    return this.db.other.findUnique({
      where: {id}
    });
  }

  update(id: number, updateOtherDto: UpdateOtherDto) {
    return this.db.other.update({
      where: {id},
      data: updateOtherDto
    })
  }

  updateByProductID(id: number, updateOtherDto: UpdateOtherDto) {
    console.log("other update")
    console.log(id)
    console.log(updateOtherDto)
    return this.db.tea.update({
      where: {
        productId: id
      },
      data: updateOtherDto
    });
  }

  remove(id: number) {
    return this.db.other.delete({
      where: {id}
    });
  }
}
