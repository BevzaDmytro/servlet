import { Injectable } from '@angular/core';
import {User} from "./User";
import {Observable, of} from "rxjs/index";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Response} from "./response";
import {Payment} from "./payment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root',
})
export class HttpService {

  private  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };
  private userUrl = 'http://localhost:8080/';

  login(email: string, password: string) {
    const params = new HttpParams().set('email', email).set('password',password);
    console.log("LOGIN");
    this.http.post(this.userUrl+'login', params)
      .subscribe((resp: any) => {
        localStorage.setItem('auth', resp.token);
      });
}

  getResponse(): Observable<Response> {
    const myHeaders = new HttpHeaders().set('auth', localStorage.getItem('auth'));
    const params = new HttpParams().set('auth', localStorage.getItem('auth'));
    console.log(localStorage.getItem('auth'));
    // return this.http.post<Response>(this.userUrl,myHeaders);
    return this.http.post<Response>(this.userUrl,params);
    // console.log("Response: "+this.response());
    // return this.response.user;
    // return this.http.get<User>(this.userUrl);
  }

  pay(payment: Payment): Observable<Response>{
    const myHeaders = new HttpHeaders().set('auth', localStorage.getItem('auth'));
    const params = new HttpParams().set('senderCard', payment.senderCard).set('recipient',payment.recipientCard).set('amount',payment.amount).set('auth', localStorage.getItem('auth'));
    // return this.http.post<Response>('http://localhost:8080/pay',{params: {payment}});
    return this.http.post<Response>('http://localhost:8080/pay',  params);
  }

  constructor(private http:HttpClient, private router:Router) { }

  logout() {
    const params = new HttpParams().set('auth', localStorage.getItem('auth'));
    localStorage.clear();
    this.http.post(this.userUrl+'logout', params).subscribe();
  }

  block(cardNum : string): Observable<Response>{
    const params = new HttpParams().set('cardToBlock', cardNum).set('auth',localStorage.getItem('auth'));
    return this.http.post(this.userUrl+'block', params);
  }
}
