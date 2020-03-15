import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { FooterComponent } from './footer/footer.component';
import { MenuService } from './main-menu/menu.data-provider.service';
import { HttpClientModule } from '@angular/common/http';
import { MenuEntryDirective } from './main-menu/menu-entry.directive';
import { MenuSubitemDirective } from './main-menu/menu-subitem.directive';
import { LoginComponent } from './login/login.component';
import { AuthService } from './login/auth.service';
import { AuthInterceptor } from './login/auth.interceptor';

@NgModule({
    declarations: [
        AppComponent,
        TopBarComponent,
        MainMenuComponent,
        FooterComponent,
        MenuEntryDirective,
        MenuSubitemDirective,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        ReactiveFormsModule
    ],
    providers: [MenuService, AuthService, AuthInterceptor],
    bootstrap: [AppComponent]
})
export class AppModule { }
