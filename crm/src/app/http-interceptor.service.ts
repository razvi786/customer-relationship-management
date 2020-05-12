import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor(private auth:AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): import("rxjs").Observable<HttpEvent<any>> {
    let authToken:string;
    if(authToken=this.auth.getAuthToken()){
      req=req.clone({
        setHeaders:{
          Authorization: authToken
        },
        withCredentials:true
      })
    }
    return next.handle(req);
  }
  
}
