import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private cardNum:string;
  private message:string;

  constructor(private userService : HttpService) { }

  // getCards(): void{
  //   this.userService.getResponse().subscribe(response => this.user = response.user);
  // }
  ngOnInit() {
  }

  unblock() {
    if (this.cardNum == null) return;
    this.userService.unblock(this.cardNum)
      .subscribe(
        response => {
          this.message = response.message
        },
        error => console.log("ERROR")
      );
  }

}
