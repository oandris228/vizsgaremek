import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { OthersService } from './others.service';
import { CreateOtherDto } from './dto/create-other.dto';
import { UpdateOtherDto } from './dto/update-other.dto';
import { ApiExcludeEndpoint, ApiNoContentResponse, ApiOkResponse, ApiParam } from '@nestjs/swagger';

@Controller('others')
export class OthersController {
  constructor(private readonly othersService: OthersService) {}

  @Post()
  @ApiExcludeEndpoint() // product controller handles the creation, only use if that doesn't work
  create(@Body() createOtherDto: CreateOtherDto) {
    return this.othersService.create(createOtherDto);
  }

  /**
   * returns all other
   * @returns json
   */
  @Get()
  @ApiOkResponse({description: 'The data was successfully returned'})
  @ApiNoContentResponse({description: 'The data was not found'})
  findAll() {
    return this.othersService.findAll();
  }

  /**
   * returns one other
   * @param id the ID of the other
   * @returns json
   */
  @Get(':id')
  @ApiOkResponse({description: 'The data was successfully returned'})
  @ApiNoContentResponse({description: 'The data was not found'})
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the other'
  })
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
