import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, Inject, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

import { AccountService, AuthService, UserInfo } from '../api';

@Component({
    selector: 'app-account',
    template: `
        <main class="island">
            @if (userInfo$ | async; as userInfo) {
            <div class="c-title">
                <h2 class="text-title">Hello, {{ userInfo.username }}</h2>
            </div>
            }

            <div class="c-split">
                <span class="text-split">Public endpoint:</span>
                @if (responsePublic$ | async; as response) {
                <span class="text-split">{{ response }}</span>
                }
            </div>

            <hr class="i-sep" />

            <div class="c-split">
                <span class="text-split">CLick here to</span>
                <a href="#" (click)="logout($event)" class="link-split">logout</a>
            </div>
        </main>
    `,
    styleUrls: ['./auth.scss'],
    standalone: true,
    imports: [CommonModule, RouterLink],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountComponent implements OnInit {
    public readonly userInfo$ = new BehaviorSubject<UserInfo | null>(null);
    public readonly responsePublic$ = new BehaviorSubject<string>('');

    constructor(
        @Inject(AccountService) private readonly accountService: AccountService,
        @Inject(AuthService) private readonly authService: AuthService,
        @Inject(Router) private readonly router: Router,
    ) {}

    public ngOnInit(): void {
        this.loadData(this.accountService.getPublic(), this.responsePublic$);

        this.accountService.getUser().subscribe({
            next: (user) => this.userInfo$.next(user),
        });
    }

    public logout(ev: Event): void {
        ev.preventDefault();

        this.authService.logout().subscribe({
            next: () => this.router.navigate(['login']),
        });
    }

    private loadData(response$: Observable<string>, subject: BehaviorSubject<string>) {
        response$.subscribe({
            next: (result) => subject.next(result),
            error: (err: HttpErrorResponse) => subject.next(`[http status: ${err.status}]`),
        });
    }
}
