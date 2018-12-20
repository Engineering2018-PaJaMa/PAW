import { Injectable } from '@angular/core';
import {Http,Response, RequestOptions, Headers} from '@angular/http';
import {map, catchError} from 'rxjs/operators';
import { Card } from '../card';
import { List } from '../list';
import { Observable } from 'rxjs';

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

getOnePageList(BoarId: number)
{
    return this.http.get(this._url+"/boardParent/" + BoarId ).pipe(map((response:Response)=>response.json()));

}

postList(list:List): Observable<List>
{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post<List>(this._urlPost, JSON.stringify(list), options);
}


deleteList(name:string)
{
    this.http.delete(this._url +"/"+ name).subscribe(r=>{console.log(r)});
}

}
