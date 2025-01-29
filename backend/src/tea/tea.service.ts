import { Injectable } from '@nestjs/common';
import { CreateTeaDto } from './dto/create-tea.dto';
import { UpdateTeaDto } from './dto/update-tea.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class TeaService {

  constructor(private readonly db: PrismaService) {}


  create(createTeaDto: CreateTeaDto) {
    return this.db.tea.create({
      data: createTeaDto
    });
  }

  findAll() {
    return this.db.tea.findMany();
  }

  findOne(id: number) {
    return this.db.tea.findUnique({
      where: {id}
    });
  }

  update(id: number, updateTeaDto: UpdateTeaDto) {
    console.log("tea update")
    console.log(id)
    console.log(updateTeaDto)
    return this.db.tea.update({
      where: {id},
      data: updateTeaDto
    });
  }

  remove(id: number) {
    return this.db.tea.delete({
      where: {id}
    });
  }
}
