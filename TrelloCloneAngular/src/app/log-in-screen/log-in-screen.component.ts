import { Component, OnInit } from '@angular/core';
import { AppServiceService } from '../app-service.service';
import { User } from '../user';

@Component({
  selector: 'app-log-in-screen',
  templateUrl: './log-in-screen.component.html',
  styleUrls: ['./log-in-screen.component.css']
})
export class LogInScreenComponent implements OnInit {

  private User;

  constructor(private service: AppServiceService) { }

  ngOnInit() {
  }

  logIn(username,password) {
    var user  = new User(username,password);

    this.service.sendUser(user);
  }

}
