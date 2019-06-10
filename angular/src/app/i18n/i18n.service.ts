import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class I18nService {
    static endpoint = '';
    constructor(private http: HttpClient) {

    }

    getTexts() {
        this.http.get(I18nService.endpoint);
    }
}
