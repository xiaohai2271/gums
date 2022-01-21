import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DumsUserMsComponent } from './dums-user-ms.component';
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    DumsUserMsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: DumsUserMsComponent }])
  ]
})
export class DumsUserMsModule {}
