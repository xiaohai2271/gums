import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _token: string = '';

  constructor() { }


  set token(token: string) {
    this._token = token;
    localStorage.setItem('_t', token);
  }

  get token(): string {
    if (!this._token) {
      this._token = localStorage.getItem('_t') || '';
    }
    return this._token;
  }


  clearToken() {
    this._token = '';
    localStorage.removeItem('_t');
  }

}
