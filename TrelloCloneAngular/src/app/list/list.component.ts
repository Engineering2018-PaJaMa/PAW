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

  @Input() listId: number;

  @Input() name: string;
  @Input() desc: string;
  newList = new Card;
  cards: Card[];
  list1: List = {
     id: 1,
     boardId: 1,
    name: "x",
     description: "x",
    position: 1,
    visibility: "visible"
  }
  constructor(private _ListService:ListService,
    private _CardService:CardService) { }

  ngOnInit() {
    this._CardService.getCardsById(this.listId.toString())
    .subscribe(_boardsList => this.cards = _boardsList); 
    console.log(this.listId);
  }

  addCard(name)
  {
    this.newList.name = name;
    this.newList.description = "desc";
    this.newList.id = this.cards.length +1;
    this.newList.listId = this.listId;
    this.newList.visibility = "VISIBLE";
    this.newList.position = this.cards.length +1;
    this._CardService.postCard(this.newList).subscribe(card => {this.cards.push(card)});
  }

  deleteCard(card){
    this._CardService.archiveCard(card);
  }
}
