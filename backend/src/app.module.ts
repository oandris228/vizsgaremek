import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { ProductsModule } from './products/products.module';
import { OrdersModule } from './orders/orders.module';
import { AuthModule } from './auth/auth.module';
import { TeaModule } from './tea/tea.module';
import { OthersModule } from './others/others.module';

@Module({
  imports: [UsersModule, ProductsModule, OrdersModule, AuthModule, TeaModule, OthersModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
