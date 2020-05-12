import { Injectable } from '@angular/core';
import { Customer } from './customermodel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerserviceService {

  constructor(private http:HttpClient) { }

  private baseUrl="http://localhost:8765/customer-service/customers"

  createCustomer(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>(this.baseUrl,customer)
  }

  getCustomerById(id:number):Observable<Customer>{
    return this.http.get<Customer>(this.baseUrl+"/"+id)
  }
  
}
