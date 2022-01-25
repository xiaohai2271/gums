import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GumsPrmMsComponent } from './gums-prm-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [GumsPrmMsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: GumsPrmMsComponent }])
  ]
})
export class GumsPrmMsModule {}
