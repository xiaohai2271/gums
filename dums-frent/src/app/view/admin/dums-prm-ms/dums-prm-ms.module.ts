import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DumsPrmMsComponent } from './dums-prm-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [DumsPrmMsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: DumsPrmMsComponent }])
  ]
})
export class DumsPrmMsModule {}
