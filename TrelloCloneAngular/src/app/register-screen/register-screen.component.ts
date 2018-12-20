import { Component, OnInit } from '@angular/core';
import { AppUserService } from 'src/app/app-userService.service';
import { User } from '../user';

@Component({
  selector: 'app-register-screen',
    templateUrl: './register-screen.component.html',
  styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {

  user: User;

  constructor(private service: AppUserService) { }

  ngOnInit() {
  }

  register(username: string,password: string) {
    this.user = new User(1,username,password)
    this.service.register(username, password);
  }

}
