import * as $ from "jquery";

import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 

import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { AuthService } from './login/auth.service';
import { MenuDataProviderService } from './main-menu/menu-data-provider.service';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { FooterComponent } from './footer/footer.component';
import { MenuEntryDirective } from './main-menu/menu-entry.directive';
import { UserListComponent } from './user/user-list/user-list.component';
import { ArticuloListComponent } from './articulo/articulo-list/articulo-list.component';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: 'login', component: LoginComponent },
      { path: 'articulo/listado', component: ArticuloListComponent },
    ], 
    { enableTracing: true }),
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    TopBarComponent,
    MainMenuComponent,
    FooterComponent,
    UserListComponent,
    MenuEntryDirective,
    ArticuloListComponent
  ],
  providers: [AuthService, MenuDataProviderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
