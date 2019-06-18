import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { User } from './user.model';
import { tap } from 'rxjs/operators';
import * as moment from 'moment';

@Injectable()
export class AuthService {
    API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';

    constructor(private http: HttpClient) {
    }

    login(alias: string, password: string) {
        return this.http.post<User>(this.API_ENDPOINT + '/login', {},
            {
                headers: new HttpHeaders().set('Content-Type', 'application/json'),
                params: new HttpParams().set('user', alias).set('pass', password),
            })
            .pipe(tap(res => this.setSession(res)));
    }

    private setSession(authResult) {
        const expiresAt = moment().add(authResult.expiresIn, 'second');
        localStorage.setItem('id_token', authResult.idToken);
        localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
    }

    logout() {
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
    }

    public isLoggedIn() {
        return moment().isBefore(this.getExpiration());
    }

    public isLoggedOut() {
        return !this.isLoggedIn();
    }

    private getExpiration() {
        const expiration = localStorage.getItem('expires_at');
        if (expiration) {
            return moment(JSON.parse(expiration));
        } else {
            return -1;
        }
    }
}
