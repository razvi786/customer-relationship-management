import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Customer } from '../customermodel';
import { CustomerserviceService } from '../customerservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-cus',
  templateUrl: './search-cus.component.html',
  styleUrls: ['./search-cus.component.css']
})
export class SearchCusComponent implements OnInit {

  constructor(private customerService:CustomerserviceService, private router:Router) { }

  id: FormControl;
  customerId:number;
  customer:Customer;

  ngOnInit(): void {
    this.id = new FormControl('');
  }

  searchCustomer(){
    this.customerId=this.id.value
    this.customerService.getCustomerById(this.id.value).subscribe(data=>{
      this.customer=data;
    })
  }

  viewSubscriptions(){
    this.router.navigate(['view-subscription',this.customerId])
  }
  createSubscriptions()
  {
    this.router.navigate(['create-subscription',this.customerId])
  }

}
