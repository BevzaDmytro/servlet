import { Component, OnInit } from '@angular/core';
import {User} from "../entities/User";
import {HttpService} from "../http.service";

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  user : User;

  constructor(private userService : HttpService) { }

  getUser(): void{
    this.userService.getUser().subscribe(response => {console.log(response);this.user = response});
  }
  ngOnInit() {
    this.getUser();
  }

  isCardBlocked(card) {
    console.log("Card: "+card.cardNum+" "+card.isBlocked)
    return card.isBlocked == true;
  }
}
