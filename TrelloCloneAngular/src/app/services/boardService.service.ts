import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Board } from '../board';

@Injectable()

export class BoardService {

private _url:string="http://localhost:8080/boards";
private _urlPost:string="http://localhost:8080/boards/create";
private _urlDelete:string="http://localhost:8080/boards/";
headers = new Headers({ 'Content-Type': 'application/json' });
options = new RequestOptions({ headers: this.headers });

constructor(private http: Http) { }


getBoards()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

postBoard(board:Board)
{
    
    this.http.post(this._urlPost, JSON.stringify(board), this.options).subscribe(r=>{console.log(r)});
}

closeBoard(board:Board)
{
    this.http.put(this._url+"/close/"+board.name,JSON.stringify(board),this.options);
}

deleteBoard(name:string)
{
    this.http.delete(this._urlDelete+name).subscribe(r=>{console.log(r)});
}

renameBoard(board:Board)
{
    this.http.post(this._url+"/rename/"+board.name,JSON.stringify(board),this.options);
}

}
