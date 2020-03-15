import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Menu } from './menu.model';
import { throwError, Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable()
export class MenuService {

    API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';

    constructor(private http: HttpClient) { }

    getMenu(code: string, user: string): Observable<Menu> {
        return this.http.get<Menu>(this.API_ENDPOINT + '/menu/' + code + '/' + user).pipe(retry(3), catchError(this.handleError));
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
        }
        // return an observable with a user-facing error message
        return throwError('Something bad happened; please try again later.');
    }
}
