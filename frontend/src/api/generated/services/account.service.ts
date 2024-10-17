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

import { UserInfo } from '../models/user-info';

@Injectable()
export class AccountService {
  constructor(
    @Inject(API_ROOT_URL_TOKEN) private rootUrl: string,
    @Inject(HttpClient) private http: HttpClient,
  ) {}

  /** Path part for operation `getPublic` */
  private static readonly GetPublicPath = '/account/check/public';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getPublic()` instead.
   *
   * This method doesn't expect any request body.
   */
  public getPublic$Response(params?: {
  },
  context?: HttpContext): Observable<StrictHttpResponse<string>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.GetPublicPath, 'get');
    if (params) {
    }

    return this.http.request(
      rb.build({ responseType: 'text', accept: 'text/plain', context: context })
    ).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) =>
        r as StrictHttpResponse<string>,
      ),
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), use `getPublic$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  public getPublic(params?: {
  },
  context?: HttpContext): Observable<string> {
    return this.getPublic$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>) => r.body as string),
    );
  }

  /** Path part for operation `getUser` */
  private static readonly GetUserPath = '/account/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  public getUser$Response(params?: {
  },
  context?: HttpContext): Observable<StrictHttpResponse<UserInfo>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.GetUserPath, 'get');
    if (params) {
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context: context })
    ).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) =>
        r as StrictHttpResponse<UserInfo>,
      ),
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), use `getUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  public getUser(params?: {
  },
  context?: HttpContext): Observable<UserInfo> {
    return this.getUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserInfo>) => r.body as UserInfo),
    );
  }

}
