import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    { path: 'home', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(
        routes,
        { enableTracing: true }
    )
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }
