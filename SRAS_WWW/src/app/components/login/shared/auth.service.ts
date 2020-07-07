import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { LoginUtilService } from './login-util.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class AuthService {

  constructor(
    private http: Http,
    private loginUtilService: LoginUtilService
  ) { }

  // login 하자마자 실행되는 함수
  login(userId: string, password: string): Promise<any> {
    return this.http.post(`${environment.apiBaseUrl}/users/login`,{userId:userId, password:password})
          .toPromise()
          .then(this.loginUtilService.checkSuccess)// api호출 성공여부 확인
          .then(response => {
            localStorage.setItem('token', response.data['token']);
            localStorage.setItem('userId', userId);
          })// login성공 시 token과 행번을 localStorage에 저장하기
          .catch(this.loginUtilService.handleApiError);// error처리
  }

}