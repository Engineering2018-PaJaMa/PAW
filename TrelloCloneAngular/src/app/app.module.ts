import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { RouterModule, Routes} from'@angular/router';
import { ListComponent } from './list/list.component';
import { CardComponent } from './card/card.component';


@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    ListComponent,
    CardComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {path:'',component: BoardComponent},
      {path:'list',component: ListComponent},
      {path:'card',component: CardComponent},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
