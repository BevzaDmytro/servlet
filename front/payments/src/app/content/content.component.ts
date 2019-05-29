import { Component, OnInit } from '@angular/core';
import {User} from "../User";
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
    this.userService.getResponse().subscribe(response => this.user = response.user);
  }
  ngOnInit() {
    this.getUser();
  }

}
