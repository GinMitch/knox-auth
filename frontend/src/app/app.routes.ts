import { Route } from '@angular/router';

import { AccountComponent } from './account.component';
import { LoginComponent } from './login.component';
import { SignupComponent } from './signup.component';

export const appRoutes: Route[] = [
    { path: 'account', component: AccountComponent },
    { path: 'login', component: LoginComponent },
    { path: 'signup', component: SignupComponent },
    { path: '', pathMatch: 'full', redirectTo: 'login' },
];
