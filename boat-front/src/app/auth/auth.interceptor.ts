import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let authToken = sessionStorage.getItem('token');
    console.log(authToken);
    if (authToken) {
      const modifiedReq = request.clone({
        headers: request.headers.set('Authorization', `Basic ${authToken}`),
      });
      return next.handle(modifiedReq);
    } else {
      return next.handle(request);
    }
  }
}
