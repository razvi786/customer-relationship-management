import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateSubscriptionComponent } from './create-subscription.component';



const routes: Routes = [
  {
    path: '',
    component: CreateSubscriptionComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CreateSubscriptionComponentRoutingModule {}
