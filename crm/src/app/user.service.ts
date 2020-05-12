import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  private baseUrl="http://localhost:8765/customer-service/users";

  createUser(user:User):Observable<User>{
    return this.http.post<User>(this.baseUrl,user)
  }

  getUserByEmailAndPassword(username:string,password:string,headers:HttpHeaders):Observable<User>{
    return this.http.get<User>(this.baseUrl+"/"+username+"/"+password,{headers})
  }
  
}
