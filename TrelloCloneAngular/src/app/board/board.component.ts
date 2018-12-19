import { Component, OnInit } from '@angular/core';
import { CardService } from '../services/cardServices.service';
import { Card } from '../card';
import { List } from '../list';
import { ListService } from '../services/listService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {

  lists: List[];
  newList = new List;

  constructor(private _ListService:ListService,private router : Router) {
    }

    ngOnInit() {
    this._ListService.getList()
    .subscribe(_boardsList => this.lists = _boardsList);    
    }

    addCard(name:string,desc:string)
    {
      this.newList.name = name;
      this.newList.description = desc;
      this.newList.boardId = this.lists.length + 1;
      this.newList.visibility = "VISIBLE";
      this.router.navigateByUrl('home');
      this._ListService.postList(this.newList);
    }

    deleteCard(name:string)
    {
      this._ListService.deleteList(name);
    }
}
