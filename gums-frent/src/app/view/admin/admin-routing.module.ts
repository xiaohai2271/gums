import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadChildren: () => import('./gums-dashboard/gums-dashboard.module').then(m => m.GumsDashboardModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./gums-user-ms/gums-user-ms.module').then(m => m.GumsUserMsModule)
  },
  {
    path: 'role',
    loadChildren: () => import('./gums-role-ms/gums-role-ms.module').then(m => m.GumsRoleMsModule)
  },
  {
    path: 'prm',
    loadChildren: () => import('./gums-prm-ms/gums-prm-ms.module').then(m => m.GumsPrmMsModule)
  },
  {
    path: 'service',
    loadChildren: () => import('./gums-service-ms/gums-service-ms.module').then(m => m.GumsServiceMsModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
