import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenubarComponent } from './menubar/menubar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpInterceptorService } from './http-interceptor.service';
import { DatePipe } from '@angular/common';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    MenubarComponent,
    FooterComponent
    /*LoginComponent,
    MenubarComponent,
    CusDetailsComponent,
    AdminAfterLoginComponent,
    HomeComponent,
    LogoutComponent,
    SearchCusComponent,
    CreateSubscriptionComponent,
    ViewSubscriptionComponent*/
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule ,
    HttpClientModule,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS, useClass:HttpInterceptorService, multi:true
    },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
