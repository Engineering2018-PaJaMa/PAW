import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Card } from '../card';

@Injectable()

export class CardService {

private _url:string="http://localhost:8080/cards";
private _urlPost:string="http://localhost:8080/cards/create";
constructor(private http: Http) { }


getCards()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

postCard(card:Card)
{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    this.http.post(this._urlPost, JSON.stringify(card), options).subscribe(r=>{});
}

}
