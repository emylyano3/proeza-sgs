import { DOCUMENT } from '@angular/common';
import { Component, OnInit, Inject, Renderer2 } from '@angular/core';

@Component({
    selector: 'app-top-bar',
    templateUrl: './top-bar.component.html',
    styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

    constructor(
        @Inject(DOCUMENT) private document: Document,
        private renderer: Renderer2
    ) { }

    private showingMenu = true;

    ngOnInit() {
    }

    toggleMenu() {
        if (this.showingMenu) {
            this.renderer.addClass(this.document.body, 'hide-sidebar');
        } else {
            this.renderer.removeClass(this.document.body, 'hide-sidebar');
        }
        this.showingMenu = !this.showingMenu;
    }

    hasSearch() {
        return false;
    }
}
