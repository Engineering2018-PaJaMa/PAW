import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AppAuthenticationServis {

  constructor(private http: HttpClient) { }

login(username: string, password:string) {
    return this.http.post(`/users/authenticate` ,{username: username,password:password});
    
    // if(user==correct)
    //localStorage.setItem('currentUser',user)
    }

logout(){
    localStorage.removeItem('currentUser');
}

}