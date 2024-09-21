import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { catchError, EMPTY, first, Subject } from 'rxjs';

import { AuthService } from '../api';

@Component({
    selector: 'app-login',
    template: `
        <main class="island">
            <div class="c-title">
                <h2 class="text-title">Welcome back!<br />Nice to see you again</h2>
            </div>

            <form [formGroup]="form" (ngSubmit)="submit()" class="c-form">
                <div class="w-input">
                    <label for="username" class="f-label">Username</label>
                    <input
                        formControlName="username"
                        id="username"
                        type="text"
                        autocomplete="username"
                        autocapitalize="off"
                        autocorrect="off"
                        spellcheck="false"
                        class="f-input"
                    />
                </div>

                <div class="w-input">
                    <label for="password" class="f-label">Password</label>
                    <input
                        formControlName="password"
                        id="password"
                        type="password"
                        autocomplete="current-password"
                        autocapitalize="off"
                        autocorrect="off"
                        spellcheck="false"
                        class="f-input"
                    />
                </div>

                <div class="w-button">
                    <button type="submit" class="btn-primary">Sign in</button>
                </div>

                @if (errors$|async; as errorMessage) {
                <span class="text-error">{{ errorMessage }}</span>
                }
            </form>

            <hr class="i-sep" />

            <div class="c-split">
                <span class="text-split">Don't have an account?</span>
                <a routerLink="/signup" [state]="{ username: form.value.username }" class="link-split">Sign up now</a>
            </div>
        </main>
    `,
    styleUrls: ['./auth.scss'],
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterLink],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent implements OnInit {
    public readonly errors$ = new Subject<string>();

    public readonly form = this.fb.nonNullable.group({
        username: this.fb.nonNullable.control<string>('', [Validators.required, Validators.pattern('[\\da-zA-Z_-]+')]),
        password: this.fb.nonNullable.control<string>('', [Validators.required, Validators.minLength(6)]),
    });

    constructor(
        @Inject(AuthService) private readonly authService: AuthService,
        @Inject(FormBuilder) private readonly fb: FormBuilder,
        @Inject(Router) private readonly router: Router,
    ) {}

    public ngOnInit(): void {
        const navUsername = this.router.lastSuccessfulNavigation?.extras?.state?.['username'];
        if (navUsername) {
            this.form.patchValue({ username: navUsername });
        }
    }

    public submit(): void {
        if (!this.form.valid) {
            this.form.markAsDirty();
            this.errors$.next('The form is invalid');
            this.form.valueChanges.pipe(first()).subscribe(() => this.errors$.next(''));
            return;
        }

        const formValue = this.form.getRawValue();

        this.authService
            .login({ body: { username: formValue.username, password: formValue.password } })
            .pipe(catchError(this.handleHttpError))
            .subscribe({
                next: () => this.router.navigate(['account']),
                error: (code: string) => this.errors$.next(loginErrors[code] ?? loginErrors['_']),
            });
    }

    private handleHttpError(err: HttpErrorResponse) {
        return err.error instanceof Blob
            ? err.error.text().then((message: string) => {
                  throw message;
              })
            : EMPTY;
    }
}

const loginErrors: Record<string, string> = {
    _: 'An unexpected error occurred. Please try again in a moment.',
    user_not_found: 'Such user was not found. Please check your username or sign up.',
    password_mismatch: 'The password is incorrect. Please double-check it.',
};
