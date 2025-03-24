import { Module } from '@nestjs/common';
import { CommissionsService } from './commissions.service';
import { CommissionsController } from './commissions.controller';
import { PrismaService } from 'src/prisma.service';

@Module({
  controllers: [CommissionsController],
  providers: [CommissionsService, PrismaService],
})
export class CommissionsModule {}
