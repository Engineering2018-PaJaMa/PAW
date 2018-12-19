import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Card } from '../card';
import { List } from '../list';

@Injectable()

export class ListService {

private _url:string="http://localhost:8080/lists";
private _urlPost:string="http://localhost:8080/lists/create";
constructor(private http: Http) { }


getList()
{
    return this.http.get(this._url)
    .pipe(map((response:Response)=>response.json()));
}

postList(list:List)
{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    this.http.post(this._urlPost, JSON.stringify(list), options).subscribe(r=>{});
}

}
