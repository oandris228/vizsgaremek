import { PartialType } from '@nestjs/mapped-types';
import { CreateOtherDto } from './create-other.dto';

export class UpdateOtherDto extends PartialType(CreateOtherDto) {}
