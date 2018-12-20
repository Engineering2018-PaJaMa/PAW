import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {RequestOptions} from '@angular/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

constructor(private http: HttpClient) { }

getById(user: User) {
    return this.http.get(`http://localhost:8080/users/` + user.name)
    .subscribe(
        res => {
          console.log(res);
        },
        data =>{
            console.log(data);
        });;
}

register(username: string, password: string) {

        this.http.post(`http://localhost:8080/users/register`, {
             "username": `${username}`,
             "password": `${password}`
             }).subscribe(r=>{console.log(r)});
}

update(user: User) {
    return this.http.put(`/users/` + user.id, user);
}

}
