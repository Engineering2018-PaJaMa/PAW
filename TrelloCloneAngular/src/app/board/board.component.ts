import { Component, OnInit } from '@angular/core';
import { Board } from '../board';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    boards: Array<Board>;
    name:String;
    desc:String;
    
  constructor() {
    this.boards = [];
   }

   addBoard(new_name,new_desc){
    let board = new Board(new_name,new_desc);
    this.boards.push(board);
  }

  ngOnInit() {
  }

}
