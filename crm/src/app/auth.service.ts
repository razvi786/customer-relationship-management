import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpClient, HttpHeaders } from '@angular/common/http';
import { UserService } from './user.service';

import {map} from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { User } from './User';

// const url=environment.server + "user-service/login"

@Injectable({
  providedIn: 'root'
})
export class AuthService implements HttpInterceptor{

  intercept(req: import("@angular/common/http").HttpRequest<any>, next: import("@angular/common/http").HttpHandler): import("rxjs").Observable<import("@angular/common/http").HttpEvent<any>> {
    throw new Error("Method not implemented.");
  }

  constructor(private userService:UserService, private httpClient:HttpClient) { }

  private baseUrl="http://localhost:8765/customer-service/users"

  authenticate(username:string,password:string):Observable<User>{

    let uname:string="admin"
    let pwd:string="crm_admin123"
    let authToken="Basic "+window.btoa(uname+":"+pwd);
    let headers=new HttpHeaders({
      Authorization: authToken
    });

    return this.userService.getUserByEmailAndPassword(username,password,headers).pipe(

      //success function
      map((successData: User)=>{
        sessionStorage.setItem("userId",successData.id.toString());
        sessionStorage.setItem("username",username);
        sessionStorage.setItem("token",authToken);
        sessionStorage.setItem("role",successData.userType);
        return successData;
      }),

      //failure function
      map(failureData=>{
        return failureData;
      })
      
    )

  }

  isUserLoggedIn():boolean{
    let userId=sessionStorage.getItem("userId")
    if(userId==null)
      return false
    return true
  }

  isAdmin():boolean{
    let role=sessionStorage.getItem("role")
    if(role=="ROLE_ADMIN")
      return true
    else
      return false
  }

  getAuthToken() {
    if (this.isUserLoggedIn())
      return sessionStorage.getItem("token");
    return null;
  }

  logout() {
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("token")
    sessionStorage.removeItem("role")
  }

}
