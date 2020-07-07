import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class TokenService {

  constructor(
    private router: Router
  ) { }

  // localStorage에 저장된 token가져오기
  getToken(): string{
    return localStorage.getItem('token');
  }
  
  // login 유무를 반환(로그인 유무에따라 보여질 페이지를 결정할 때 사용예정)
  isLoggedIn(): boolean {
    var token = localStorage.getItem('token');
    if(token) return true;
    else return false;
  }
  
  // 로그아웃
  logout(): void {
    localStorage.removeItem('token');// token정보 삭제
  }

}
