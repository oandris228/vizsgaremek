import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Request, Req } from '@nestjs/common';
import { AuthService } from './auth.service';
import { CreateAuthDto, LoginDto } from './dto/create-auth.dto';
import { TokenAuthGuard } from './auth.guard';
import { UsersService } from 'src/users/users.service';


//will finish later
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService,
    private readonly usersService: UsersService
  ) {}

  @Post()
  login(@Body() LoginDto: LoginDto) {
    return this.authService.signIn(LoginDto.username, LoginDto.password);
  }

  @UseGuards(TokenAuthGuard)
  @Get()
  getProfile(@Req() req) {
    return this.usersService.findOneByName(req.user.username);
  }
}
