import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Menu } from './menu.model';
import { AppComponent } from '../app.component';

@Injectable()
export class MenuService {

    API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';

    constructor(private http: HttpClient) { }

    getMenu(code: string, user: string) {
        return this.http.get<Menu>(this.API_ENDPOINT + '/menu/' + code + '/' + user);
    }
}
