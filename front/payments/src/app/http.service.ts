import { Injectable } from '@angular/core';
import {User} from "./entities/User";
import {Observable, of} from "rxjs/index";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Response} from "./entities/response";
import {Payment} from "./entities/payment";
import {Router} from "@angular/router";
import {Card} from "./entities/card";

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
    return this.http.post<Response>(this.userUrl,params);

  }

  getUser(): Observable<User> {
    const myHeaders = new HttpHeaders().set('auth', localStorage.getItem('auth'));
    const params = new HttpParams().set('auth', localStorage.getItem('auth'));
    console.log(localStorage.getItem('auth'));
    return this.http.post<User>(this.userUrl+"user",params);
  }


  getCards(): Observable<Card[]> {
    const myHeaders = new HttpHeaders().set('auth', localStorage.getItem('auth'));
    const params = new HttpParams().set('auth', localStorage.getItem('auth'));
    console.log(localStorage.getItem('auth'));
    // return this.http.post<Response>(this.userUrl,myHeaders);
    return this.http.post<Card[]>(this.userUrl+"cards",params);
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
    return this.http.post<Response>(this.userUrl+'block', params);
  }

  unblock(cardNum : string): Observable<Response>{
    const params = new HttpParams().set('unblockCard', cardNum).set('auth',localStorage.getItem('auth'));
    return this.http.post<Response>(this.userUrl+'unblock', params);
  }
}
