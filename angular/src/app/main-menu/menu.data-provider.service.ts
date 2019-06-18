import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Menu } from './menu.model';
import { AppComponent } from '../app.component';

@Injectable()
export class MenuService {

    constructor(private http: HttpClient) { }

    getMenu(code: string, user: string) {
        return this.http.get<Menu>(AppComponent.API_ENDPOINT + '/menu/' + code + '/' + user);
    }
}
