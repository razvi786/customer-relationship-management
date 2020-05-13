import { Component, OnInit } from '@angular/core';
import { Customer } from '../customermodel';
import { Router } from '@angular/router';
import { NgForm, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomerserviceService } from '../customerservice.service';
//import { CustomerserviceService } from '../customerservice.service';

@Component({
  selector: 'app-cus-details',
  templateUrl: './cus-details.component.html',
  styleUrls: ['./cus-details.component.css']
})
export class CusDetailsComponent implements OnInit {

  constructor(private database:CustomerserviceService, private router: Router, private formBuilder:FormBuilder) { }

  customerForm:FormGroup;

  ngOnInit(): void {
    this.customerForm=this.formBuilder.group({
      name:['',Validators.required],
      mobileNumber:['',Validators.required],
      email:['',Validators.required],
      circle:['Andhra Pradesh',Validators.required],
      dp:['../../assets/images/default.png']
    })
  }
  addCustomer(){
    this.database.createCustomer(this.customerForm.value).subscribe(data=>{
      alert("Customer Created")
      this.customerForm.setControl("name",new FormControl('',Validators.required))
      this.customerForm.setControl("mobileNumber",new FormControl('',Validators.required))
      this.customerForm.setControl("email",new FormControl('',Validators.required))
      this.customerForm.setControl("circle",new FormControl('Andhra Pradesh',Validators.required))
    });
  }

}
