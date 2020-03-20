import { Component, OnInit } from '@angular/core';
import { MenuModel } from './menu-model';
import { MenuDataProviderService } from './menu-data-provider.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  constructor(private menuService : MenuDataProviderService) {
      
  }

  menu: MenuModel;

  ngOnInit(): void {
    this.menuService.getMenu('MAIN', 'admin').subscribe(data => this.menu = data);
  }

}
