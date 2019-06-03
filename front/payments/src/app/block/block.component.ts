import { Component, OnInit } from '@angular/core';
import {Payment} from "../entities/payment";
import {HttpService} from "../http.service";
import {User} from "../entities/User";

@Component({
  selector: 'app-block',
  templateUrl: './block.component.html',
  styleUrls: ['./block.component.css']
})
export class BlockComponent implements OnInit {

  cards;
  private cardNum:string;
  private message:string;

  constructor(private userService : HttpService) { }

  getCards(): void{
    this.userService.getCards().subscribe(response => this.cards = response);
  }
  ngOnInit() {
    this.getCards();
  }

  onCardChange(value: any) {
    this.cardNum = value;
  }

  block(){
    if(this.cardNum == null) return;
    this.userService.block(this.cardNum)
      .subscribe(
        response => {this.message = response.message},
        error => console.log("ERROR")
      );
  }

  messageExist(){
    return this.message != null;
  }
}
