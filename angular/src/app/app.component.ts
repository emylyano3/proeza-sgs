import { Component } from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    static API_ENDPOINT = 'http://localhost:8080/proeza-sgs/api';

    title = 'angular';
}
