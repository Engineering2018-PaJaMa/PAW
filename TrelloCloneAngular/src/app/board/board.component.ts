import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
import { AppServiceService } from '../app-service.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {

    board: Board;
    name:String;
    desc:String;
    
  constructor(private service: AppServiceService) {
    }

   addBoard(new_name,new_desc){
     this.board.name = new_name;
     this.board.desc = new_desc;

     this.service.saveBoard(this.board)
     .subscribe(() => this.board);
  }

  ngOnInit() {
  }

}
