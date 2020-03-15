import { Component, OnInit } from '@angular/core';
import { Menu } from './menu.model';
import { MenuService } from './menu.data-provider.service';

@Component({
    selector: 'app-main-menu',
    templateUrl: './main-menu.component.html',
    styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

    menu: Menu;

    constructor(private service: MenuService) {
        this.menu = new Menu();
    }

    ngOnInit() {
        this.service.getMenu('MAIN', 'admin').subscribe(data => this.menu = data);
    }
}
