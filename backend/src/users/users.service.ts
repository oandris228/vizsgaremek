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
      data: createUserDto
    });
  }

  findAll() {
    return this.db.user.findMany();
  }

  findOneByName(username: string) {
    return this.db.user.findUnique({
      where: {username: username}
    });
  }

  findOne(id: number) {
    return this.db.user.findUnique({
      where: {id}
    });
  }

  async findUserByToken(token: string) {
    const tokenData = await this.db.token.findUnique({
      where: { token },
      include: { user: true }
    })
    if (!tokenData) return null;
    const user = tokenData.user;
    delete user.password;
    
    return user;
  }

  update(username: string, updateUserDto: UpdateUserDto) {
    return this.db.user.update({
      where: {username: username},
      data: updateUserDto
    });
  }

  remove(username: string) {
    return this.db.user.delete({
      where: {username}
    });
  }
}
