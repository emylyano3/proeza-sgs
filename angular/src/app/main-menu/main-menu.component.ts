import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { MenuModel } from './menu-model';
import { MenuDataProviderService } from './menu-data-provider.service';

declare var $:any;

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent implements OnInit, AfterViewChecked {

  constructor(private menuService : MenuDataProviderService) {
  }
  ngAfterViewChecked(): void {
    $('#side-menu').metisMenu('dispose');
    $('#side-menu').metisMenu();
  }

  menu: MenuModel;

  ngOnInit(): void {
    this.menuService.getMenu('MAIN', 'admin').subscribe(data => this.menu = data);
  }
}
