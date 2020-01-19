import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsfeedService {
  rooturl: any;
  constructor(private http: HttpClient) {
    this.rooturl = `http://localhost:3000`
   }

   getTopNews(){
    return this.http.get(`${this.rooturl}/topnews`);
   }
}
