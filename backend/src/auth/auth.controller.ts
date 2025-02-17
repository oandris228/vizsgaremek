import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Request } from '@nestjs/common';
import { AuthService } from './auth.service';
import { CreateAuthDto, LoginDto } from './dto/create-auth.dto';
import { UpdateAuthDto } from './dto/update-auth.dto';
import { ApiBearerAuth, ApiExcludeController } from '@nestjs/swagger';
import { AuthGuard } from './auth.guard';


//will finish later
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post()
  login(@Body() LoginDto: LoginDto) {
    return this.authService.signIn(LoginDto.username, LoginDto.password);
  }

  @UseGuards(AuthGuard)
  @ApiBearerAuth()
  @Get('profile')
  getProfile(@Request() req) {
    return req.user;
  }

  @UseGuards(AuthGuard)
  @ApiBearerAuth()
  @Delete('logout')
  logout(@Request() req) {
    return this.authService.logout(req.token);
  }
}
