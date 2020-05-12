import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewSubscriptionComponent } from './view-subscription.component';



const routes: Routes = [
  {
    path: '',
    component: ViewSubscriptionComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewSubscriptionComponentRoutingModule {}
