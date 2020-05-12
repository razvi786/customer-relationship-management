import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from './subscriptionmodel';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionserviceService {

  constructor(private http:HttpClient) { }

  private baseUrl="http://localhost:8765/customer-service/subscriptions";
  private baseUrl2="http://localhost:8765/customer-service/customers"
  
  createSubscription(subscription:Subscription):Observable<Subscription>{
    return this.http.post<Subscription>(this.baseUrl,subscription)
  }

  viewActiveSubscriptionsOfCustomer(id:number):Observable<Subscription[]>{
    return this.http.get<Subscription[]>(this.baseUrl2+"/"+id+"/subscriptions/active")
  }
  
}
