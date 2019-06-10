import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { FooterComponent } from './footer/footer.component';
import { MenuService } from './main-menu/menu.data-provider.service';
import { HttpClientModule } from '@angular/common/http';
import { MenuEntryDirective } from './main-menu/menu-entry.directive';
import { MenuSubitemDirective } from './main-menu/menu-subitem.directive';

@NgModule({
    declarations: [
        AppComponent,
        TopBarComponent,
        MainMenuComponent,
        FooterComponent,
        MenuEntryDirective,
        MenuSubitemDirective
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule
    ],
    providers: [MenuService],
    bootstrap: [AppComponent]
})
export class AppModule { }
