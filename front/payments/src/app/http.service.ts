import { Injectable } from '@angular/core';
import {User} from "./User";
import {Observable, of} from "rxjs/index";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Response} from "./response";
import {Payment} from "./payment";

@Injectable({
  providedIn: 'root',
})
export class HttpService {

  private userUrl = 'http://localhost:8080/';
  // private response: Observable<Response>;

  getResponse(): Observable<Response> {
    return this.http.get<Response>(this.userUrl);
    // console.log("Response: "+this.response());
    // return this.response.user;
    // return this.http.get<User>(this.userUrl);
    // return of( {
    //   "id":1,"name":"Dima","email":"qwerty@gmail.com","password":"12345","isAdmin":false,"cards":[{"id":1,"cardNum":"0000 0000","cardOwner":1,"account":{"id":1,"accountNum":0,"balance":200.0},"isBlocked":false},{"id":2,"cardNum":"0000 0001","cardOwner":1,"account":{"id":2,"accountNum":1,"balance":100.1},"isBlocked":false}]
    // });
  }

  pay(payment: Payment): Observable<Response>{
    // const myHeaders = new HttpHeaders().set('Authorization', 'my-auth-token'),;
    const params = new HttpParams().set('senderCard', payment.senderCard).set('recipient',payment.recipientCard).set('amount',payment.amount);
    // return this.http.post<Response>('http://localhost:8080/pay',{params: {payment}});
    return this.http.post<Response>('http://localhost:8080/pay',params);
  }

  constructor(private http:HttpClient) { }
}
