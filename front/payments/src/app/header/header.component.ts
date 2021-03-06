import { Component, OnInit } from '@angular/core';
import {User} from "../entities/User";
import {HttpService} from "../http.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user : User;


  constructor(private userService : HttpService) { }


  getUser(): void{
    this.userService.getUser().subscribe(response => this.user = response);
  }

  ngOnInit() {
    this.getUser();
    console.log(this.user);
  }

  logout() {
    this.userService.logout();
  }

  isUserAdmin() {
    console.log(this.user.admin);
    return this.user.admin == true;
  }
}
