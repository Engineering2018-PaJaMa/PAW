import { Component, OnInit } from '@angular/core';
import { AppUserService } from 'src/app/app-userService.service';
import { User } from '../user';

@Component({
  selector: 'app-register-screen',
  templateUrl: './register-screen.component.html',
  styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {

  constructor(private service: AppUserService) { }

  ngOnInit() {
  }

  register(id,username,password) {
    var user = new User(id,username,password)
    this.service.register(user);
  }

}
