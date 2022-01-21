import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DumsServiceMsComponent } from './dums-service-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    DumsServiceMsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: DumsServiceMsComponent }])
  ]
})
export class DumsServiceMsModule {}
