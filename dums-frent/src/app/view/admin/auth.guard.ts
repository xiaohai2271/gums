import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate, Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from "../../service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let token = this.authService.token
    if (!token) {
      this.router.navigateByUrl("/login").then(() => null);
      return false
    }
    // todo: checkToken
    return !!token;
  }
}
