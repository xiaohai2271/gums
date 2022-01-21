import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { DumsRoleMsComponent } from "./dums-role-ms.component";


@NgModule({
  declarations: [DumsRoleMsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: DumsRoleMsComponent }])
  ]
})
export class DumsRoleMsModule {}
