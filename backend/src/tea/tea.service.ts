import { Injectable } from '@nestjs/common';
import { CreateTeaDto } from './dto/create-tea.dto';
import { UpdateTeaDto } from './dto/update-tea.dto';

@Injectable()
export class TeaService {
  create(createTeaDto: CreateTeaDto) {
    return 'This action adds a new tea';
  }

  findAll() {
    return `This action returns all tea`;
  }

  findOne(id: number) {
    return `This action returns a #${id} tea`;
  }

  update(id: number, updateTeaDto: UpdateTeaDto) {
    return `This action updates a #${id} tea`;
  }

  remove(id: number) {
    return `This action removes a #${id} tea`;
  }
}
