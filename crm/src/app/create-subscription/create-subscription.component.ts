import { Component, OnInit } from '@angular/core';
import { SubscriptionserviceService } from '../subscriptionservice.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Subscription } from '../subscriptionmodel';
import { CustomerserviceService } from '../customerservice.service';
import { Customer } from '../customermodel';
import { formatDate, DatePipe } from '@angular/common';

@Component({
  selector: 'app-create-subscription',
  templateUrl: './create-subscription.component.html',
  styleUrls: ['./create-subscription.component.css']
})
export class CreateSubscriptionComponent implements OnInit {

  constructor(private subscriptionService:SubscriptionserviceService, private formBuilder:FormBuilder, private router: Router, private route:ActivatedRoute, private datePipe:DatePipe) { }

  id:number;
  subscriptionForm:FormGroup;
  expiryDate:string;

  ngOnInit(): void {
    this.id=+this.route.snapshot.paramMap.get("customerId")
    let date:Date=new Date();
    this.expiryDate=this.datePipe.transform(date,'yyyy-MM-dd')
    this.subscriptionForm=this.formBuilder.group({
      name:['',Validators.required],
      expiryDate:[this.expiryDate,Validators.required],
      id:[this.id,Validators.required]
    })
  }

  addSubscription(){
    let subscription:Subscription=new Subscription();
    subscription.name=this.subscriptionForm.controls.name.value;
    subscription.expiryDate=this.subscriptionForm.controls.expiryDate.value;
    subscription.active=true;
    let customer:Customer=new Customer();
    customer.customerId=this.subscriptionForm.controls.id.value;
    subscription.customerId=customer;
    
    this.subscriptionService.createSubscription(subscription).subscribe(data=>{
      alert("subscription added")
      this.subscriptionForm.setControl('name',new FormControl('',Validators.required))
      this.subscriptionForm.setControl('expiryDate',new FormControl(this.expiryDate,Validators.required))
    });

  }

}
