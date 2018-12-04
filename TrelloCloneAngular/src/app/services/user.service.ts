import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../user';

// interface between angular and backend

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`http://localhost:8080/users`);
    }

    getById(id: number) {
        return this.http.get(`http://localhost:8080/users/${id}`);
    }

    register(user: User) {
        return this.http.post(`http://localhost:8080/users/register`, user);
    }

    update(user: User) {
        return this.http.put(`http://localhost:8080/users/${user.id}`, user);
    }

    delete(id: number) {
        return this.http.delete(`http://localhost:8080/users/${id}`);
    }
}