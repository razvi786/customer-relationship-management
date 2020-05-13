import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateSubscriptionComponentRoutingModule } from './create-subscription-routing.module';
import { CreateSubscriptionComponent } from './create-subscription.component';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CreateSubscriptionComponentRoutingModule,
  ],
  declarations: [CreateSubscriptionComponent]
})
export class CreateSubscriptionComponentModule {}
