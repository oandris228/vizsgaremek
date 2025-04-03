import { Injectable } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { PrismaService } from 'src/prisma.service';
import * as bcrypt from 'bcrypt';

@Injectable()
export class UsersService {
  constructor(private readonly db: PrismaService) {}

  async create(createUserDto: CreateUserDto) {
    createUserDto.password = await bcrypt.hash(createUserDto.password, 10);
    return this.db.user.create({
      data: {
        ...createUserDto,
        role: 'User'
      }
    });
  }

  findAll() {
    return this.db.user.findMany({
      include: {commissions: true}
    });
  }

  findOneByName(username: string) {
    return this.db.user.findUnique({
      where: {username: username},
      include: {commissions: true}
    });
  }

  findOne(id: number) {
    return this.db.user.findUnique({
      where: {id},
      include: {commissions: true}
    });
  }

  async findUserByToken(token: string) {
    const tokenData = await this.db.token.findUnique({
      where: { token }
    })
    if (!tokenData) return null;
    const id = tokenData.userId;
    const user = await this.db.user.findUnique({
      where: {id},
      include: {commissions: true}
    })
    delete user.password;
    
    return user;
  }

  update(id: number, updateUserDto: UpdateUserDto) {
    return this.db.user.update({
      where: {id},
      data: updateUserDto
    });
  }

  remove(id: number) {
    return this.db.user.delete({
      where: {id}
    });
  }
}
