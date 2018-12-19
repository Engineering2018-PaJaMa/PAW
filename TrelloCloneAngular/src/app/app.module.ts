import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { RouterModule, Routes} from'@angular/router';
import { ListComponent } from './list/list.component';
import { CardComponent } from './card/card.component';
import { HomeComponent } from './home/home.component';
import { LogInScreenComponent } from './log-in-screen/log-in-screen.component';
import { RegisterScreenComponent } from './register-screen/register-screen.component';
import { HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    ListComponent,
    CardComponent,
    HomeComponent,
    LogInScreenComponent,
    RegisterScreenComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot([
      {path:'',component: LogInScreenComponent},
      {path:'register',component: RegisterScreenComponent},
      {path:'home',component: HomeComponent},
      {path:'board/:id',component: BoardComponent,pathMatch:'full'},
      {path:'list',component: ListComponent},
      {path:'card',component: CardComponent},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
