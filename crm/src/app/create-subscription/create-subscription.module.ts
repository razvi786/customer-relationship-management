import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateSubscriptionComponent } from './create-subscription.component';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CreateSubscriptionComponentModule,
  ],
  declarations: [CreateSubscriptionComponent]
})
export class CreateSubscriptionComponentModule {}
