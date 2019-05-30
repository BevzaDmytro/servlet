import { Component, OnInit } from '@angular/core';
import {Payment} from "../payment";
import {HttpService} from "../http.service";
import {User} from "../User";

@Component({
  selector: 'app-block',
  templateUrl: './block.component.html',
  styleUrls: ['./block.component.css']
})
export class BlockComponent implements OnInit {

  user : User;
  private cardNum:string;
  private message:string;

  constructor(private userService : HttpService) { }

  getUser(): void{
    this.userService.getResponse().subscribe(response => this.user = response.user);
  }
  ngOnInit() {
    this.getUser();
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
