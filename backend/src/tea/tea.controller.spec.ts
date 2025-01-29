import { Test, TestingModule } from '@nestjs/testing';
import { TeaController } from './tea.controller';
import { TeaService } from './tea.service';

describe('TeaController', () => {
  let controller: TeaController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [TeaController],
      providers: [TeaService],
    }).compile();

    controller = module.get<TeaController>(TeaController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
