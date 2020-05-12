import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LogoutComponentRoutingModule } from './logout-routing.module';
import { LogoutComponent } from './logout.component';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LogoutComponentRoutingModule,
  ],
  declarations: [LogoutComponent]
})
export class LogoutComponentModule {}
