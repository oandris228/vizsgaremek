import { Injectable } from '@nestjs/common';
import { CreateCommissionDto } from './dto/create-commission.dto';
import { UpdateCommissionDto } from './dto/update-commission.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class CommissionsService {

  constructor(private readonly db: PrismaService) {}

  create(createCommissionDto: CreateCommissionDto) {
    return this.db.commission.create({
      data: createCommissionDto
    });
  }

  findAll() {
    return this.db.commission.findMany( {
      include: {items: true}
    });
  }

  findAllActive(id: number) {
    return this.db.commission.findMany( {
      where: {commissionState: "Active", user_id: id},
      include: {items: true}
    });
  }

  findOne(id: number) {
    return this.db.commission.findUnique({
      where: {id},
      include: {items: true}
    });
  }

  async findActive() {
    const activeCommissions = await this.db.commission.findMany({ where: { commissionState: "Active" }, include: {items: true} });
    return activeCommissions[0];
  }

  update(id: number, updateCommissionDto: UpdateCommissionDto) {
    return this.db.commission.update({
      where: {id},
      data: updateCommissionDto
    });
  }

  async remove(id: number) {

    const removeItems = await this.db.item.deleteMany({
      where: {commissionId: id}
    })
    return this.db.commission.delete({
      where: {id}
    });
  }
}
