import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { TeaService } from './tea.service';
import { CreateTeaDto } from './dto/create-tea.dto';
import { UpdateTeaDto } from './dto/update-tea.dto';
import { ApiExcludeEndpoint } from '@nestjs/swagger';

@Controller('tea')
export class TeaController {
  constructor(private readonly teaService: TeaService) {}

  @Post()
  @ApiExcludeEndpoint() // product controller handles the creation, only use if that doesn't work
  create(@Body() createTeaDto: CreateTeaDto) {
    return this.teaService.create(createTeaDto);
  }

  @Get()
  findAll() {
    return this.teaService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.teaService.findOne(+id);
  }

  @Patch(':id')
  @ApiExcludeEndpoint() // product controller handles the patching, only use if that doesn't work
  update(@Param('id') id: string, @Body() updateTeaDto: UpdateTeaDto) {
    return this.teaService.update(+id, updateTeaDto);
  }

  @Delete(':id') 
  @ApiExcludeEndpoint() // product controller handles the deletion, only use if that doesn't work
  remove(@Param('id') id: string) {
    return this.teaService.remove(+id);
  }
}
