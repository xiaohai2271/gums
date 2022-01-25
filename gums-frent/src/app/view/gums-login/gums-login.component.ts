import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../service/auth.service";

@Component({
  selector: 'view-gums-login',
  templateUrl: './gums-login.component.html',
  styleUrls: ['./gums-login.component.less']
})
export class GumsLoginComponent implements OnInit {

  user = {
    username: '',
    password: '',
    isRememberMe: true
  };

  private url: string = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }


  login() {
    this.authService.token = '123';
  }

  forgotPwd() {

  }

  register() {

  }
}
