import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { OrdersService } from './orders.service';
import { CreateOrderDto } from './dto/create-order.dto';
import { UpdateOrderDto } from './dto/update-order.dto';
import { ApiBadRequestResponse, ApiParam, ApiResponse } from '@nestjs/swagger';

@Controller('orders')
export class OrdersController {
  constructor(private readonly ordersService: OrdersService) {}


  /**
   * 
   * Creates an order.
   * 
   * @param createOrderDto the DTO for the order
   * @returns json of the order
   */
  @Post()
  @ApiResponse({ status: 201, description: 'A new random was created' })
  @ApiBadRequestResponse({ description: 'The supplied data was invalid' })
  create(@Body() createOrderDto: CreateOrderDto) {
    return this.ordersService.create(createOrderDto);
  }

  /**
   * Returns all orders.
   */
  @Get()
  @ApiResponse({status: 200, description: 'The data was successfully returned'})
  @ApiResponse({status: 500, description: 'An error was encountered'})
  findAll() {
    return this.ordersService.findAll();
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
    return this.ordersService.findOne(+id);
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
  update(@Param('id') id: string, @Body() updateOrderDto: UpdateOrderDto) {
    return this.ordersService.update(+id, updateOrderDto);
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
    return this.ordersService.remove(+id);
  }
}
