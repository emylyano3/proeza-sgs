import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  login(alias: string, password: string) {
    // return this.http.post<Auth>(this.API_ENDPOINT + '/login', {},
    //   {
    //     headers: new HttpHeaders().set('Content-Type', 'application/json'),
    //     params: new HttpParams().set('user', alias).set('pass', password),
    //   })
    //   .pipe(
    //     tap(aut => this.setSession(aut)),
    //     catchError(this.handleError)
    //   );
    
  }
  public isLoggedIn() {
    // return moment().isBefore(this.getExpiration());
    return false;
  }

  public isLoggedOut() {
    return true;
    // return !this.isLoggedIn();
  }
}
