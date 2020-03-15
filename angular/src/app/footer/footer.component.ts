import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-footer',
    template: `
    <footer class="footer">
        <span class="pull-right" i18n="@@app.description">
            Stock Management System
        </span>
        <span i18n="@@app.copyright">2015-2019 Copyright</span>
    </footer>
    `,
    styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

    constructor() { }

    ngOnInit() {
    }
}
