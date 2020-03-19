import { DOCUMENT } from '@angular/common';
import { Component, OnInit, Inject, Renderer2 } from '@angular/core';
// import { faBars } from '@fortawesome/free-solid-svg-icons';
// import { faEraser } from '@fortawesome/free-solid-svg-icons';
// import { faUser } from '@fortawesome/free-solid-svg-icons';
// import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
// import { faSignInAlt } from '@fortawesome/free-solid-svg-icons';
// import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-top-bar',
    templateUrl: './top-bar.component.html',
    styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

    // faBars = faBars;
    // faEraser = faEraser;
    // faUser = faUser;
    // faCaretDown = faCaretDown;
    // faSignInAlt = faSignInAlt;
    // faSignOutAlt = faSignOutAlt;

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
