import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Card } from '../card';
import { Comments } from '../comment';
import { Observable } from 'rxjs';

@Injectable()

export class CardService {

private _url:string="http://localhost:8080/cards";
private _urlPost:string="http://localhost:8080/cards/create";
private _urlComment:string="http://localhost:8080/comments/create";
private _urlGetComments:string="http://localhost:8080/comments";

headers = new Headers({ 'Content-Type': 'application/json' });
options = new RequestOptions({ headers: this.headers });

constructor(private http: Http) { }


getCards()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

getCardsById(id: string)
{
    return this.http.get(this._url + "/listParent/" + id)
    .pipe(map((response:Response)=>response.json()));
}

postCard(card:Card): Observable<any>
{
    return  this.http.post(this._urlPost, JSON.stringify(card), this.options);
}

renameCard(card:Card)
{
    this.http.post(this._url + "/rename/"+card.name,JSON.stringify(card),this.options).subscribe(r=>{console.log(r)});
}

modifyCard(card:Card)
{
    this.http.post(this._url + "/modify",JSON.stringify(card),this.options).subscribe(r=>{console.log(r)});
}

archiveCard(card:Card)
{
    this.http.post(this._url + "/archive/"+card.name,JSON.stringify(card),this.options).subscribe(r=>{console.log(r)});
}

addComment(comments:Comments)
{
    this.http.post(this._urlComment,JSON.stringify(comments),this.options).subscribe(r=>{console.log(r)});
}

getAllComments()
{
    return this.http.get(this._urlGetComments)
    .pipe(map((response:Response)=>response.json()));
}

deleteComment(name:string)
{
    this.http.delete(this._urlGetComments+"/"+name).subscribe(r=>{console.log(r)});
}
}
