import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BoatsListComponent} from './boats-list/boats-list.component';
import {BoatDetailsComponent} from './boat-details/boat-details.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {NotifierModule} from "angular-notifier";

@NgModule({
  declarations: [
    AppComponent,
    BoatsListComponent,
    BoatDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NotifierModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
