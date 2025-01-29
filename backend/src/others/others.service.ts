import { Injectable } from '@nestjs/common';
import { CreateOtherDto } from './dto/create-other.dto';
import { UpdateOtherDto } from './dto/update-other.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class OthersService {

  constructor(private readonly db: PrismaService) {}

  create(createOtherDto: CreateOtherDto) {
    return "Action in another service";
  }

  findAll() {
    return `This action returns all others`;
  }

  findOne(id: number) {
    return `This action returns a #${id} other`;
  }

  update(id: number, updateOtherDto: UpdateOtherDto) {
    return `This action updates a #${id} other`;
  }

  remove(id: number) {
    return `This action removes a #${id} other`;
  }
}
