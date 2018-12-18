import { Component, OnInit } from '@angular/core';
import { Board } from '../board';
import {BoardService} from '../services/boardService.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {

    boards: Board[];
    newBoard: Board;

  constructor(private _BoardService:BoardService) {
    }

    ngOnInit() {
    }

}
