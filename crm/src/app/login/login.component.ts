import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService, private formBuilder:FormBuilder, private router:Router) { }

  loginForm:FormGroup;

  ngOnInit():void{
    this.loginForm=this.formBuilder.group({
      email:['',Validators.required],
      password:['',Validators.required]
    })
  }

  validateLogin(){
    let email = this.loginForm.controls.email.value;
    let password = this.loginForm.controls.password.value;
    this.authService.authenticate(email,password).subscribe(data =>{
      this.router.navigate(['/search-cus'])
    }, error=>{
      console.log(error)
      alert("Invalid Credentials")
    })
  }

}
