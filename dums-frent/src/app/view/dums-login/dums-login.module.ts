import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { DumsLoginComponent } from "./dums-login.component";
import { FormsModule } from "@angular/forms";
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzFormModule } from "ng-zorro-antd/form";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzCheckboxModule } from "ng-zorro-antd/checkbox";


@NgModule({
  declarations: [DumsLoginComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: DumsLoginComponent }]),
    FormsModule,
    NzButtonModule,
    NzFormModule,
    NzInputModule,
    NzCheckboxModule
  ]
})
export class DumsLoginModule {}
