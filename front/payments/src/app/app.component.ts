import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'payments';

  isUserLoggedIn(){
    let token = localStorage.getItem('auth');
    if(token == null) return false;
    else return true;
  }
}
