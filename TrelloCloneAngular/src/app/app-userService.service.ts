import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

register(user: User) {
    return this.http.post(`http://localhost:8080/users/register`, {
    "username":user.name,
    "password":user.pass
    })
    .subscribe(
        res => {
          console.log(res);
        });
}

update(user: User) {
    return this.http.put(`/users/` + user.id, user);
}

}
