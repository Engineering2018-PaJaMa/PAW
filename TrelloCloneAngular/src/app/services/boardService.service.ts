import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Board } from '../board';

@Injectable()

export class BoardService {

private _url:string="http://localhost:8080/boards";
private _urlPost:string="http://localhost:8080/boards/create";
constructor(private http: Http) { }


getBoards()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

postBoard(board:Board)
{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    this.http.post(this._urlPost, JSON.stringify(board), options).subscribe(r=>{});
}

}
