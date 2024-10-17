/* tslint:disable */
/* eslint-disable */

/**
 * This file was generated automatically from API specification.
 * Manual changes to this file may cause incorrect behavior and will be lost when the code is regenerated.
 * To update this file run the generation tool.
 */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';

import { API_ROOT_URL_TOKEN } from '../api-configuration';
import { RequestBuilder, StrictHttpResponse } from '../request-builder';

import { LoginRequest } from '../models/login-request';
import { SignupRequest } from '../models/signup-request';

@Injectable()
export class AuthService {
  constructor(
    @Inject(API_ROOT_URL_TOKEN) private rootUrl: string,
    @Inject(HttpClient) private http: HttpClient,
  ) {}

  /** Path part for operation `signup` */
  private static readonly SignupPath = '/auth/signup';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `signup()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  public signup$Response(params: {
    body: SignupRequest;
  },
  context?: HttpContext): Observable<StrictHttpResponse<string>> {

    const rb = new RequestBuilder(this.rootUrl, AuthService.SignupPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(
      rb.build({ responseType: 'blob', accept: '*/*', context: context })
    ).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) =>
        r as StrictHttpResponse<string>,
      ),
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), use `signup$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  public signup(params: {
    body: SignupRequest;
  },
  context?: HttpContext): Observable<string> {
    return this.signup$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>) => r.body as string),
    );
  }

  /** Path part for operation `logout` */
  private static readonly LogoutPath = '/auth/logout';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `logout()` instead.
   *
   * This method doesn't expect any request body.
   */
  public logout$Response(params?: {
  },
  context?: HttpContext): Observable<StrictHttpResponse<string>> {

    const rb = new RequestBuilder(this.rootUrl, AuthService.LogoutPath, 'post');
    if (params) {
    }

    return this.http.request(
      rb.build({ responseType: 'blob', accept: '*/*', context: context })
    ).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) =>
        r as StrictHttpResponse<string>,
      ),
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), use `logout$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  public logout(params?: {
  },
  context?: HttpContext): Observable<string> {
    return this.logout$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>) => r.body as string),
    );
  }

  /** Path part for operation `login` */
  private static readonly LoginPath = '/auth/login';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `login()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  public login$Response(params: {
    body: LoginRequest;
  },
  context?: HttpContext): Observable<StrictHttpResponse<string>> {

    const rb = new RequestBuilder(this.rootUrl, AuthService.LoginPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(
      rb.build({ responseType: 'blob', accept: '*/*', context: context })
    ).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) =>
        r as StrictHttpResponse<string>,
      ),
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), use `login$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  public login(params: {
    body: LoginRequest;
  },
  context?: HttpContext): Observable<string> {
    return this.login$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>) => r.body as string),
    );
  }

}
