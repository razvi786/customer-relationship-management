import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchCusComponent } from './search-cus.component';




const routes: Routes = [
  {
    path: '',
    component: SearchCusComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SearchCusComponentRoutingModule {}
