import { Controller, Get, Post, Body, Patch, Param, Delete, Request, UseGuards } from '@nestjs/common';
import { UsersService } from './users.service';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { ApiBearerAuth, ApiExcludeController } from '@nestjs/swagger';
import { TokenAuthGuard } from 'src/auth/auth.guard';


//will finish later
@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Post()
  create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }

  @Get()
  findAll() {
    return this.usersService.findAll();
  }

  @UseGuards(TokenAuthGuard)
  @Get('token')
  @ApiBearerAuth()
  getProfile(@Request() req) {
    console.log(req.token)
    return this.usersService.findUserByToken(req.token);
  }

  @Get(':id')
  findOne(@Param('username') id: string) {
    return this.usersService.findOneByName(id);
  }

  @Patch(':id')
  update(@Param('username') username: string, @Body() updateUserDto: UpdateUserDto) {
    return this.usersService.update(username, updateUserDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.usersService.remove(id);
  }
}
