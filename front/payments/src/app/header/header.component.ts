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

  constructor(private userService : HttpService) { }

  getUser(): void{
    this.userService.getResponse().subscribe(response => this.user = response.user);
  }

  ngOnInit() {
    this.getUser();
  }

}
