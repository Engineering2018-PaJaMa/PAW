import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Board } from './board';

@Injectable({
  providedIn: 'root'
})
export class AppServiceService {

  private serverUrl = '';
  
  constructor(private http: HttpClient) { }

  getBoards(): Observable<Board[]>{
    return this.http.get<Board[]>(this.serverUrl)
  }

  saveBoard(board: Board): Observable<any>{
    return this.http.put(this.serverUrl, board);
  }


}
