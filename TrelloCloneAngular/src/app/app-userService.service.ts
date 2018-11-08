import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

constructor(private http: HttpClient) { }

getById(id: number) {
    return this.http.get(`/users/` + id);
}

register(user: User) {
    return this.http.post(`/users/register`, user);
}

update(user: User) {
    return this.http.put(`/users/` + user.id, user);
}

}
