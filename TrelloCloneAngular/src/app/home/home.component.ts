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
  newBoard = new Board;
constructor(private _BoardService:BoardService) {
  }

  ngOnInit() {
    this._BoardService.getBoards()
    .subscribe(_boardsList => this.boards = _boardsList);    
  }

 addBoard(name,description){
  this.newBoard.name = name;
  this.newBoard.description = description;
  this.newBoard.id = this.boards.length + 1;
  this.newBoard.userId = 1;
  this.newBoard.visibility = "VISIBLE";
  console.log(JSON.stringify(this.newBoard));
  this._BoardService.postBoard(this.newBoard);
 }

 deleteBoard(name)
 {
  this._BoardService.deleteBoard(name);
 }
}
