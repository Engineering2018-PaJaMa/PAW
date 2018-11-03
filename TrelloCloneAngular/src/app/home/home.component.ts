import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
import { AppServiceService } from '../app-service.service';

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
    this.service.getBoards()
    .subscribe(boards => this.boards = boards);
  }

}
