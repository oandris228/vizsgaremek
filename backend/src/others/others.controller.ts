import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { OthersService } from './others.service';
import { CreateOtherDto } from './dto/create-other.dto';
import { UpdateOtherDto } from './dto/update-other.dto';
import { ApiExcludeEndpoint } from '@nestjs/swagger';

@Controller('others')
export class OthersController {
  constructor(private readonly othersService: OthersService) {}

  @Post()
  @ApiExcludeEndpoint() // product controller handles the creation, only use if that doesn't work
  create(@Body() createOtherDto: CreateOtherDto) {
    return this.othersService.create(createOtherDto);
  }

  @Get()
  findAll() {
    return this.othersService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.othersService.findOne(+id);
  }

  @Patch(':id')
  @ApiExcludeEndpoint() // product controller handles the patching, only use if that doesn't work
  update(@Param('id') id: string, @Body() updateOtherDto: UpdateOtherDto) {
    return this.othersService.update(+id, updateOtherDto);
  }

  @Delete(':id')
  @ApiExcludeEndpoint() // product controller handles the deletion, only use if that doesn't work
  remove(@Param('id') id: string) {
    return this.othersService.remove(+id);
  }
}
