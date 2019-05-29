import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";
import {Payment} from "../payment";

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css']
})
export class PayComponent implements OnInit {

  private cards;
  payment = new Payment();
  message: string;

  constructor(private userService : HttpService) { }

  public getCards(){
    this.userService.getResponse().subscribe(response => this.cards = response.user.cards);
  }
  ngOnInit() {
    this.getCards();
  }

  submit(payment: Payment){
    console.log(this.payment);
    this.userService.pay(payment)
      .subscribe(
        response => {this.cards = response.user.cards; this.message = response.message},
        error => console.log("ERROR")
      );
  }

onChange(cardNum){
    this.payment.senderCard = cardNum;
    // console.log();
  }

  messageExist(){
    return this.message != null;
  }

}
