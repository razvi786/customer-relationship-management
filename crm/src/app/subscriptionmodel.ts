import { Customer } from './customermodel';

export class Subscription{
    id: number;
    name: string;
    expiryDate: Date;
    active: boolean;
    customerId: Customer;
}