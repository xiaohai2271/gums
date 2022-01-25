import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {GumsRoleMsComponent} from "./gums-role-ms.component";


@NgModule({
  declarations: [GumsRoleMsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{path: '', component: GumsRoleMsComponent}])
  ]
})
export class GumsRoleMsModule {
}
