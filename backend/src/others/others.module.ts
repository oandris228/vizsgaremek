import { Module } from '@nestjs/common';
import { OthersService } from './others.service';
import { OthersController } from './others.controller';
import { PrismaService } from 'src/prisma.service';

@Module({
  controllers: [OthersController],
  providers: [OthersService, PrismaService],
})
export class OthersModule {}
