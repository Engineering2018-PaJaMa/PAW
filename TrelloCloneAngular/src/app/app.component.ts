import { Component } from '@angular/core';
import {BoardService} from './services/boardService.service';
import { CardService } from './services/cardServices.service';
import { ListService } from './services/listService.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [BoardService,CardService,ListService]
})
export class AppComponent {
  
}
