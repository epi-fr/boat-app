import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {BoatsListComponent} from "./boats-list/boats-list.component";
import {BoatDetailsComponent} from "./boat-details/boat-details.component";
import {AuthComponent} from "./auth/auth.component";
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [
  {path: '', redirectTo: 'boat', pathMatch: 'full'},
  {
    path: 'boat',
    component: BoatsListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'boat/:id', canActivate: [AuthGuard], component: BoatDetailsComponent,
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'boat/add', canActivate: [AuthGuard], component: BoatDetailsComponent,
    runGuardsAndResolvers: 'always'
  },
  {path: 'login', component: AuthComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
