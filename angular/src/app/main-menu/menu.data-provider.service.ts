import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Menu } from './menu.model';

@Injectable()
export class MenuService {

    constructor(private http: HttpClient) { }

    static endpoint = 'http://192.168.0.182:8080/proeza-sgs/rest/application';

    getMenus() {
        return this.http.get<Menu[]>(MenuService.endpoint + '/getMenus');
    }

    getMenu(code: string, user: string) {
        return this.http.get<Menu>(MenuService.endpoint + '/menu/' + code + '/' + user);
    }
}
