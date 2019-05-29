import { Component, OnInit } from '@angular/core';
import {User} from "../User";
import {HttpService} from "../http.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user : User;
  email = '';
  password = '';

  constructor(private userService : HttpService) { }

  isUserLoggedIn(){
    let token = localStorage.getItem('auth');
    if(token == null) return false;
    else return true;
  }
  getUser(): void{
    this.userService.getResponse().subscribe(response => this.user = response.user);
  }

  ngOnInit() {
    this.getUser();
    console.log(this.user);
  }

  logIn() {
    console.log(this.email + this.password);
    this.userService.login(this.email, this.password);
  }

  logout() {
    this.userService.logout();

  }
}
