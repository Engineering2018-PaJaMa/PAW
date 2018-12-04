import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { RouterModule, Routes} from'@angular/router';
import { ListComponent } from './list/list.component';
import { CardComponent } from './card/card.component';
import { HomeComponent } from './home/home.component';
import { LogInScreenComponent } from './log-in-screen/log-in-screen.component';
import { RegisterScreenComponent } from './register-screen/register-screen.component';
import { AlertComponent } from './alert/alert.component';
import { ErrorInterceptor } from './helpers/error.interceptor';
import {JwtInterceptor} from './helpers/jwt.interceptor'
import { AuthGuard } from './auth.guard';
import {fakeBackendProvider} from './helpers/fake-backend'

const appRoutes: Routes = [
  
    {path:'board', component: BoardComponent, canActivate: [AuthGuard]},
    {path:'register', component: RegisterScreenComponent},
    {path:'login', component: LogInScreenComponent},
    {path:'board', component: BoardComponent},
    {path:'list', component: ListComponent},
    {path:'card', component: CardComponent},

    { path: '**', redirectTo: 'login' }
  
]

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    ListComponent,
    CardComponent,
    HomeComponent,
    LogInScreenComponent,
    RegisterScreenComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    // provider used to create fake backend
    fakeBackendProvider
],
  bootstrap: [AppComponent]
})
export class AppModule { }
