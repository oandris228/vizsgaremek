export class CreateUserDto {
    username: string;
    email: string;
    password: string;
    shipping_address?: string;
    role: 'User' | 'Admin';
}
