import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../service/auth.service";

@Component({
  selector: 'view-dums-login',
  templateUrl: './dums-login.component.html',
  styleUrls: ['./dums-login.component.less']
})
export class DumsLoginComponent implements OnInit {

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
