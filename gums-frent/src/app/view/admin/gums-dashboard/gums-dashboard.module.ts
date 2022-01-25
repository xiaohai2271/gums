import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GumsDashboardComponent } from './gums-dashboard.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    GumsDashboardComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path: '',
      component: GumsDashboardComponent
    }])
  ]
})
export class GumsDashboardModule {}
