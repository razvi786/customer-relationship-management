import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CusDetailsComponentRoutingModule } from './cus-details-routing.module';
import { CusDetailsComponent } from './cus-details.component';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CusDetailsComponentRoutingModule,
  ],
  declarations: [CusDetailsComponent]
})
export class CusDeatailsComponentModule {}
