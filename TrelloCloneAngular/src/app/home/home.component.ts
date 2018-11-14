import { Component, OnInit } from '@angular/core';
import { Board } from '../board';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  board : Board = {
    name:"",
    desc:""
  };
  boards : Board[];

  constructor() { }

  ngOnInit() {
    //this.board = new Board(this.name,this.desc);
  }

  deleteBoard(){
  }

  addBoard(nameBoard,descBoard){
    this.board.name = nameBoard;
    this.board.desc = descBoard;
    this.boards.fill(this.board);
  }
}
