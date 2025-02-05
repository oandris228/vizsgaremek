import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  const config = new DocumentBuilder()
    .setTitle('Tea Webshop Backend API Documentation')
    .setDescription('A basic overview of the API, for more info, check the word/ppt (does not exist atm). Not finished controllers/endpoints are also not shown. All controllers are written, but you do not need to use all of them.')
    .setVersion('1.0')
    .build();
  const documentFactory = () => SwaggerModule.createDocument(app, config);

  SwaggerModule.setup('apidoc', app, documentFactory);


  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
