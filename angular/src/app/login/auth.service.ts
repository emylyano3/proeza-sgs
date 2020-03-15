import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import * as moment from 'moment';
import { Auth } from './auth.model';
import { throwError } from 'rxjs';

@Injectable()
export class AuthService {
    API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';
    errorMessage: string;

    constructor(private http: HttpClient) {
    }

    login(alias: string, password: string) {
        return this.http.post<Auth>(this.API_ENDPOINT + '/login', {},
            {
                headers: new HttpHeaders().set('Content-Type', 'application/json'),
                params: new HttpParams().set('user', alias).set('pass', password),
            })
            .pipe(
                tap(aut => this.setSession(aut)),
                catchError(this.handleError)
            );
    }

    private setSession(authResult: Auth) {
        const expiresAt = moment().add(authResult.expiresIn, 'second');
        localStorage.setItem('id_token', authResult.token);
        localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
    }

    logout() {
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
    }

    private handleError(response: HttpErrorResponse) {
        if (response.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', response.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(`Backend returned code ${response.status}, body was: ${response.error}`);
        }
        return throwError('Something bad happened; please try again later.');
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
