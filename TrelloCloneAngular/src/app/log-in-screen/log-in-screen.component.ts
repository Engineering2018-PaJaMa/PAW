import { Component, OnInit } from '@angular/core';
import { AppServiceService } from '../app-userService.service';
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

  logIn(id,username,password) {
    var user  = new User(id,username,password);

    this.service.getById(user.id);
  }

}
