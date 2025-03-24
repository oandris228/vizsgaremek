import { Injectable } from '@nestjs/common';
import { CreateCommissionDto } from './dto/create-commission.dto';
import { UpdateCommissionDto } from './dto/update-commission.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class CommissionsService {

  constructor(private readonly db: PrismaService) {}

  create(createOrderDto: CreateCommissionDto) {
    return this.db.commission.create({
      data: createCommissionDto
    });
  }

  findAll() {
    return this.db.commission.findMany();
  }

  findOne(id: number) {
    return this.db.commission.findUnique({
      where: {id}
    });
  }

  async findActive() {
    const activeCommissions = await this.db.commission.findMany({ where: { commissionState: "Active" } });
    return activeCommissions[0];
  }

  update(id: number, updateCommissionDto: UpdateCommissionDto) {
    return this.db.commission.update({
      where: {id},
      data: updateCommissionDto
    });
  }

  remove(id: number) {
    return this.db.commission.delete({
      where: {id}
    });
  }
}
