import { provideHttpClient } from '@angular/common/http';
import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withViewTransitions } from '@angular/router';

import { API_ROOT_URL_TOKEN, ApiModule } from '../api';
import { environment } from '../environments/environment';
import { appRoutes } from './app.routes';

export const appConfig: ApplicationConfig = {
    providers: [
        provideZoneChangeDetection({ eventCoalescing: true }),
        provideHttpClient(),
        { provide: API_ROOT_URL_TOKEN, useValue: environment.apiRootUrl },
        importProvidersFrom(ApiModule),
        provideRouter(appRoutes, withViewTransitions()),
    ],
};
