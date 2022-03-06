import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BoatsListComponent} from './boats-list/boats-list.component';
import {BoatDetailsComponent} from './boat-details/boat-details.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {NotifierModule} from "angular-notifier";
import {AuthComponent} from "./auth/auth.component";
import {AuthInterceptor} from "./auth/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    BoatsListComponent,
    BoatDetailsComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NotifierModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
