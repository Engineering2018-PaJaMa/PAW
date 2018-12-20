import { Component, OnInit, Input } from '@angular/core';
import { CardService } from '../services/cardServices.service';
import { Card } from '../card';
import { List } from '../list';
import { ListService } from '../services/listService.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {


  lists: List[];
  newList = new List;

  constructor(private _ListService:ListService,private router : Router, private route: ActivatedRoute) {
    }
    @Input() boardId: number;

    ngOnInit() {
    // const id = +this.route.snapshot.paramMap.get('id');
    const id = +this.route.snapshot.paramMap.get('id');
    this._ListService.getOnePageList(id)
    .subscribe(_boardsList => this.lists = _boardsList);    
    }

    addCard(name:string,desc:string)
    {
    const id = +this.route.snapshot.paramMap.get('id');
      this.newList.name = name;
      this.newList.description = desc;
      this.newList.boardId = id;
      this.newList.visibility = "VISIBLE";
    //  this.router.navigateByUrl('home');
      this._ListService.postList(this.newList).
      subscribe(list => {this.lists.push(list)});
    }

    deleteCard(name:string)
    {
      this._ListService.deleteList(name);
    }
}
