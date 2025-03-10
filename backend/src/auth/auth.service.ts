import { Injectable, UnauthorizedException } from '@nestjs/common';
import { CreateAuthDto } from './dto/create-auth.dto';
import { UpdateAuthDto } from './dto/update-auth.dto';
import { UsersService } from 'src/users/users.service';
import { JwtService } from '@nestjs/jwt';
import * as bcrypt from 'bcrypt';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class AuthService {
  constructor(
    private readonly db: PrismaService,
    private usersService: UsersService,
    private jwtService: JwtService
  ) {}

  async signIn(username: string, pass: string): Promise<any> {
    const user = await this.usersService.findOneByName(username);
    const thing = await bcrypt.compare(pass, user.password);
    if (!thing) {
      throw new UnauthorizedException();
    }
    const { password, ...result } = user;
    const payload = { sub: user.id, username: user.username };
    const access_token = await this.jwtService.signAsync(payload)
    return this.db.token.create({
      data: {
        userId: user.id,
        token: access_token
      }
    })
  }

  async logout(token: string) {
    return this.db.token.delete({
      where: {token}
    })
  }
}
