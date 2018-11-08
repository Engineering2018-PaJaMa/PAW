import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {

    board: Board;
    name:String;
    desc:String;
    
  constructor() {
    }

   addBoard(new_name,new_desc){
     this.board.name = new_name;
     this.board.desc = new_desc;
  }

  ngOnInit() {
  }

}
