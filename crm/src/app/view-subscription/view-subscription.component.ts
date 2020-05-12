import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SubscriptionserviceService } from '../subscriptionservice.service';
import { Subscription } from '../subscriptionmodel';

@Component({
  selector: 'app-view-subscription',
  templateUrl: './view-subscription.component.html',
  styleUrls: ['./view-subscription.component.css']
})
export class ViewSubscriptionComponent implements OnInit {

  constructor(private subscriptionService:SubscriptionserviceService, private route:ActivatedRoute) { }

  id=this.route.snapshot.paramMap.get("customerId")

  subscriptions:Subscription[];

  ngOnInit(): void {
    this.subscriptionService.viewActiveSubscriptionsOfCustomer(+this.id).subscribe(data=>{
      this.subscriptions=data
    })
  }

}
