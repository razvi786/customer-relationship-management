import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchCusComponent } from './search-cus.component';
import { SearchCusComponentRoutingModule } from './search-cus-routing.module';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SearchCusComponentRoutingModule,
  ],
  declarations: [SearchCusComponent]
})
export class SearchCusComponentModule {}
