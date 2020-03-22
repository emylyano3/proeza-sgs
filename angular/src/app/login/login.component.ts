import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { AuthService } from './auth.service';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm;

    constructor(
        private fb: FormBuilder,
        // private authService: AuthService,
        // private router: Router
        ) {

        this.loginForm = this.fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    errorMessage: string;

    ngOnInit() {
        
    }

    login() {
        // const val = this.form.value;
        // if (val.username && val.password) {
        //     this.authService.login(val.username, val.password)
        //         .subscribe((response) => {
        //             console.log('User is logged in');
        //             this.router.navigateByUrl('/');
        //         });
        // }
    }
}
