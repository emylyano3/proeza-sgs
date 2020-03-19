import { Component, OnInit } from '@angular/core';
import { MenuModel } from './menu-model';
import { MenuDataProviderService } from './menu-data-provider.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  constructor(
    private menuService : MenuDataProviderService
    ) {
      this.menu = { 
        code: 'M1', 
        tooltip: 'Menu uno', 
        text: 'Menu 1', 
        items: [{ 
          text: 'Item 1',
          code: 'I1', 
          href: '/', 
          icon: 'none', 
          index: 0, 
          subitems: [
            {text: 'Sub Item 1',
            code: 'SI1', 
            href: '/', 
            icon: 'none', 
            index: 0, 
            subitems: []}
          ] 
      }] 
    };
  }

  menu: MenuModel;

  ngOnInit(): void {
    this.menuService.getMenu('MAIN', 'admin').subscribe(data => this.menu = data);
  }

}
