import { Component } from '@angular/core';
import {BoardService} from './services/boardService.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [BoardService]
})
export class AppComponent {
  
}
