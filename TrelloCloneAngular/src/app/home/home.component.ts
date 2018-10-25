import { Component, OnInit } from '@angular/core';
import { Board } from '../board';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'TrelloCloneAngular';

  constructor() { }

  ngOnInit() {
  }

}
