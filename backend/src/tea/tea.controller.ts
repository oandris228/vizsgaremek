import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { TeaService } from './tea.service';
import { CreateTeaDto } from './dto/create-tea.dto';
import { UpdateTeaDto } from './dto/update-tea.dto';
import { ApiExcludeEndpoint, ApiNoContentResponse, ApiOkResponse, ApiParam } from '@nestjs/swagger';

@Controller('tea')
export class TeaController {
  constructor(private readonly teaService: TeaService) {}

  @Post()
  @ApiExcludeEndpoint() // product controller handles the creation, only use if that doesn't work
  create(@Body() createTeaDto: CreateTeaDto) {
    return this.teaService.create(createTeaDto);
  }

  /**
   * returns all tea
   * @returns json
   */
  @Get()
  @ApiOkResponse({description: 'The data was successfully returned'})
  @ApiNoContentResponse({description: 'The data was not found'})
  findAll() {
    return this.teaService.findAll();
  }

  /**
   * returns one tea
   * @param id the ID of the tea
   * @returns json
   */
  @Get(':id')
  @ApiOkResponse({description: 'The data was successfully returned'})
  @ApiNoContentResponse({description: 'The data was not found'})
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the tea'
  })
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
