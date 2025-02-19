import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Request } from '@nestjs/common';
import { AuthService } from './auth.service';
import { CreateAuthDto, LoginDto } from './dto/create-auth.dto';
import { UpdateAuthDto } from './dto/update-auth.dto';
import { ApiBearerAuth, ApiExcludeController } from '@nestjs/swagger';
import { TokenAuthGuard } from './auth.guard';
import { JwtAuthGuard } from './jwt-auth.guard';
import { Roles } from './roles.decorator';
import { Role } from '@prisma/client';
import { RolesGuard } from './roles.guard';


//will finish later
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post()
  login(@Body() LoginDto: LoginDto) {
    return this.authService.signIn(LoginDto.username, LoginDto.password);
  }

  @UseGuards(TokenAuthGuard)
  @ApiBearerAuth()
  @Get('profile')
  getProfile(@Request() req) {
    return req.user;
  }

  @UseGuards(JwtAuthGuard, RolesGuard)
  @ApiBearerAuth()
  @Get('admin')
  @Roles(Role.Admin)
  getAdminProfile(@Request() req) {
    return req.user;
  }

  @UseGuards(TokenAuthGuard)
  @ApiBearerAuth()
  @Delete('logout')
  logout(@Request() req) {
    return this.authService.logout(req.token);
  }
}
