import { Module } from '@nestjs/common';
import { TeaService } from './tea.service';
import { TeaController } from './tea.controller';

@Module({
  controllers: [TeaController],
  providers: [TeaService],
})
export class TeaModule {}
