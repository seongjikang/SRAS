import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { TokenService } from '../shared/services/token.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private tokenService: TokenService
  ) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.tokenService.isLoggedIn()) {// 로그인 상태일 때
      return true;// true 반환하기
    }
    else {// 로그인 상태가 아닐 때
      if (state.url != "/" && state.url != "/review") {// 웹 사이트 메인 주소가 아닐 경우만
        console.log(state.url);
        alert("로그인 후 서비스를 이용해 주세요!");// alert을 띄움
      }
      this.router.navigate(['login'], { queryParams: { redirectTo: state.url } });// 로그인페이지로 이동시키기고 어떤 페이지에서 로그인 페이지로 이동시켰는지 파람으로 넘김
      return false;// false값 반환하기
    }
  }
}
