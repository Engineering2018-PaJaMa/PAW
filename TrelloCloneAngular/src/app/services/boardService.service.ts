import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Board } from '../board';

@Injectable()

export class BoardService {

private _url:string="http://localhost:8080/boards";
private _urlPost:string="http://localhost:8080/boards/create";
headers = new Headers({ 'Content-Type': 'application/json' });

constructor(private http: Http) { }


getBoards()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

postBoard(board:Board)
{
    
    this.http.post(this._urlPost, JSON.stringify(board), {headers: this.headers}).subscribe(r=>{console.log(r)});
}

}
