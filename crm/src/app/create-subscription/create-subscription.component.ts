import { Component, OnInit } from '@angular/core';
import { SubscriptionserviceService } from '../subscriptionservice.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription } from '../subscriptionmodel';
import { CustomerserviceService } from '../customerservice.service';

@Component({
  selector: 'app-create-subscription',
  templateUrl: './create-subscription.component.html',
  styleUrls: ['./create-subscription.component.css']
})
export class CreateSubscriptionComponent implements OnInit {

subscription1:Subscription=new Subscription();
constructor(private service:SubscriptionserviceService,private router: Router,private route:ActivatedRoute) { }
id=this.route.snapshot.paramMap.get("customerId")

ngOnInit(): void {
  this.subscription1.customerId=this.id;
  this.subscription1.active=true;
}
addSubscription(){
 console.log("customer Id"+this.id);
  console.log(this.subscription1);
  this.service.createSubscription(this.subscription1)
  .subscribe(subscription1=>{alert("subscription added")});
}

onSubmit(form:NgForm){
  this.addSubscription();
}
}
