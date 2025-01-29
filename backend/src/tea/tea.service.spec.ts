import { Test, TestingModule } from '@nestjs/testing';
import { TeaService } from './tea.service';

describe('TeaService', () => {
  let service: TeaService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TeaService],
    }).compile();

    service = module.get<TeaService>(TeaService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
