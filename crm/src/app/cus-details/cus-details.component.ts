import { Component, OnInit } from '@angular/core';
import { Customer } from '../customermodel';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { CustomerserviceService } from '../customerservice.service';
//import { CustomerserviceService } from '../customerservice.service';

@Component({
  selector: 'app-cus-details',
  templateUrl: './cus-details.component.html',
  styleUrls: ['./cus-details.component.css']
})
export class CusDetailsComponent implements OnInit {

  customer1: Customer= new Customer();
  constructor(private database:CustomerserviceService ,private router: Router) { }

  ngOnInit(): void {
  }
  addCustomer(){
    console.log("function");
    console.log(this.customer1);
    this.database.createCustomer(this.customer1)
  .subscribe(customer1=>{alert("CustomerAdded.")});
  this.router.navigate(['logout']);

  }
  onSubmit(form:NgForm){
    this.addCustomer();
}

}
