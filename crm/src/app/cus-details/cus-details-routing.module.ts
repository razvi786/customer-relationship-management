import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CusDetailsComponent } from './cus-details.component';



const routes: Routes = [
  {
    path: '',
    component: CusDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CusDetailsComponentRoutingModule {}
