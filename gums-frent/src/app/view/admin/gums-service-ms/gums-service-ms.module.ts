import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GumsServiceMsComponent } from './gums-service-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    GumsServiceMsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: GumsServiceMsComponent }])
  ]
})
export class GumsServiceMsModule {}
