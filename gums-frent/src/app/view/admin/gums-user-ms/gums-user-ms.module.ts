import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GumsUserMsComponent } from './gums-user-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    GumsUserMsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: GumsUserMsComponent }])
  ]
})
export class GumsUserMsModule {}
