import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {BoatsListComponent} from "./boats-list/boats-list.component";
import {BoatDetailsComponent} from "./boat-details/boat-details.component";

const routes: Routes = [
  {path: '', redirectTo: 'boats', pathMatch: 'full'},
  {path: 'boats', component: BoatsListComponent},
  {path: 'boat/:id', component: BoatDetailsComponent},
  {path: 'add', component: BoatDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
