import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DumsLoginComponent } from "./view/dums-login/dums-login.component";

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
    loadChildren: () => import('./view/admin/dums-admin.module').then(m => m.DumsDashboardModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
