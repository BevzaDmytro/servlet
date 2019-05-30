import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';
  constructor(private userService : HttpService) { }

  ngOnInit() {
  }

  logIn() {
    console.log(this.email + this.password);
    this.userService.login(this.email, this.password);
  }
}
