import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DumsDashboardComponent } from './dums-dashboard.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    DumsDashboardComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path: '',
      component: DumsDashboardComponent
    }])
  ]
})
export class DumsDashboardModule {}
