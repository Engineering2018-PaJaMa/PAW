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

    ngOnInit() {
    }

   addBoard(new_name:string,new_desc:string){
    this.board.name = new_name;
    this.board.desc = new_desc;
    return this.board.name + ""+ this.board.desc;  
  }


}
