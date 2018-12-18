import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
import {BoardService} from '../services/boardService.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 
  boards: Board[];
  newBoard: Board;
  testBoards = [];
constructor(private _BoardService:BoardService) {
  }

  ngOnInit() {
    this._BoardService.getBoards()
    .subscribe(_boardsList => this.boards = _boardsList);    
  }

 addBoard(){
  this._BoardService.postBoard(this.newBoard);
 }
}
