import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { CommissionsService } from './commissions.service';
import { CreateCommissionDto } from './dto/create-commission.dto';
import { UpdateCommissionDto } from './dto/update-commission.dto';
import { ApiBadRequestResponse, ApiParam, ApiResponse } from '@nestjs/swagger';

@Controller('commissions')
export class CommissionsController {
  constructor(private readonly commissionsService: CommissionsService) {}


  /**
   * 
   * Creates an order.
   * 
   * @param createCommissionDto the DTO for the order
   * @returns json of the order
   */
  @Post()
  @ApiResponse({ status: 201, description: 'A new random was created' })
  @ApiBadRequestResponse({ description: 'The supplied data was invalid' })
  create(@Body() createCommissionDto: CreateCommissionDto) {
    return this.commissionsService.create(createCommissionDto);
  }

  /**
   * Returns all orders.
   */
  @Get()
  @ApiResponse({status: 200, description: 'The data was successfully returned'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  findAll() {
    return this.commissionsService.findAll();
  }


  /**
   * Returns a specific order.
   * @param id the ID of the Order you're trying to get
   * @returns json of the order
   */
  @Get(':id')
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the order'
  })
  @ApiResponse({status: 200, description: 'The data was successfully returned'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  findOne(@Param('id') id: string) {
    return this.commissionsService.findOne(+id);
  }

  /**
   * Updates the data of a specific order.
   * @param id the ID of the order.
   * @param updateOrderDto the new data that replaces the old
   * @returns json
   */
  @Patch(':id')
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the order'
  })
  @ApiResponse({status: 200, description: 'The data was successfully updated'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  update(@Param('id') id: string, @Body() updateCommissionDto: UpdateCommissionDto) {
    return this.commissionsService.update(+id, updateCommissionDto);
  }

  /**
   * Deletes a specific order.
   * @param id the ID of the order
   * @returns json
   */
  @Delete(':id')
  @ApiParam({
    name: 'id',
    type: 'int',
    description: 'The unique ID of the order'
  })
  @ApiResponse({status: 200, description: 'The data was successfully deleted'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  remove(@Param('id') id: string) {
    return this.commissionsService.remove(+id);
  }
}
