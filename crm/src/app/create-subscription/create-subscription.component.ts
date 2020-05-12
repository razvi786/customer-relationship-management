import { Component, OnInit } from '@angular/core';
import { SubscriptionserviceService } from '../subscriptionservice.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription } from '../subscriptionmodel';

@Component({
  selector: 'app-create-subscription',
  templateUrl: './create-subscription.component.html',
  styleUrls: ['./create-subscription.component.css']
})
export class CreateSubscriptionComponent implements OnInit {

  subscription1: Subscription;
  constructor(private database:SubscriptionserviceService,private router: Router) { }

  ngOnInit(): void {
  }
  addSubscription(){
    console.log("function");
    console.log(this.subscription1);
    this.database.createSubscription(this.subscription1)
  .subscribe(subscription1=>{alert("Subscrition Added.")});
  this.router.navigate(['logout']);
  }
  
  onSubmit(form:NgForm){
    this.addSubscription();
}
}
