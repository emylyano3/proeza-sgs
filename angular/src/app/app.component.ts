import { Component } from '@angular/core';
import { AuthService } from './login/auth.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    static API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';

    constructor(
        private authService: AuthService) {
    }
    title = 'angular';

    isLoggedIn() {
        return this.authService.isLoggedIn();
    }
}
