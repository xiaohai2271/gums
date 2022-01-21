import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DumsLoginComponent } from "./view/dums-login/dums-login.component";
import { AuthGuard } from "./view/admin/auth.guard";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/login',
  },
  {
    path: 'login',
    component: DumsLoginComponent,
  },
  {
    path: 'admin',
    canActivate: [AuthGuard],
    loadChildren: () => import('./view/admin/dums-admin.module').then(m => m.DumsDashboardModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
