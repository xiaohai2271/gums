import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadChildren: () => import('./dums-dashboard/dums-dashboard.module').then(m => m.DumsDashboardModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./dums-user-ms/dums-user-ms.module').then(m => m.DumsUserMsModule)
  },
  {
    path: 'role',
    loadChildren: () => import('./dums-role-ms/dums-role-ms.module').then(m => m.DumsRoleMsModule)
  },
  {
    path: 'prm',
    loadChildren: () => import('./dums-prm-ms/dums-prm-ms.module').then(m => m.DumsPrmMsModule)
  },
  {
    path: 'service',
    loadChildren: () => import('./dums-service-ms/dums-service-ms.module').then(m => m.DumsServiceMsModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
