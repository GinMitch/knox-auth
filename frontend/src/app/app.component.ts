import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-root',
    template: `<router-outlet></router-outlet>`,
    styles: [
        `
            :host {
                width: 100vw;
                height: 100vh;
                display: flex;
                overflow: hidden;
            }
        `,
    ],
    standalone: true,
    imports: [RouterModule],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppComponent {}
