import { Component, OnInit, Input } from '@angular/core';
import { CardService } from '../services/cardServices.service';
import { Card } from '../card';
import { List } from '../list';
import { ListService } from '../services/listService.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input() name: string;
  @Input() desc: string;
  newList = new List;
  lists: List[];
  constructor(private _ListService:ListService) { }

  ngOnInit() {
    this._ListService.getList()
    .subscribe(_boardsList => this.lists = _boardsList); 
  }

  addList(name)
  {
    this.newList.name = name;
    this.newList.description = "desc";
    this.newList.id = this.lists.length +1;
    this.newList.visibility = "VISIBLE";
    this.newList.position = this.lists.length +1;
    this._ListService.postList(this.newList);
  }
}
