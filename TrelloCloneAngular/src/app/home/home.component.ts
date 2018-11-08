import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
import { AppServiceService } from '../app-userService.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'TrelloCloneAngular';
  boards: Array<Board>;

  constructor(private service: AppServiceService) { }

  ngOnInit() {
    this.getBoard();
  }

  getBoard(): void{
  }

}
