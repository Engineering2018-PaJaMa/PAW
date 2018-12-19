import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { CardService } from '../services/cardServices.service';
import { Card } from '../card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() name: string;
  @Input() desc: string;
  @Input() pos: number;
  cards: Card[];
  
  constructor(private _CardService:CardService) {
   }

  ngOnInit() {
    this._CardService.getCards()
    .subscribe(_boardsList => this.cards = _boardsList); 
  }

}
