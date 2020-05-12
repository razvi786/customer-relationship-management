import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewSubscriptionComponent } from './view-subscription.component';
import { ViewSubscriptionComponentRoutingModule } from './view-subscription-routing.module';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ViewSubscriptionComponentRoutingModule,
  ],
  declarations: [ViewSubscriptionComponent]
})
export class ViewSubscriptionComponentModule {}
