import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { LoginUtilService } from './shared/login-util.service';
import { AuthService } from './shared/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  redirectTo: string;// 로그인 성공시 리다이렉트 할 곳(토큰 만료시 사용할 변수)
  form: FormGroup;// userid와 password field의 control을 담는 formGroup
  
  formErrors = {
    'userId':'',
    'password':'',
  };// form field의 에러메세지를 저장하는 오브젝트
  formErrorMessages = {
    'userId' : {
      'required': '아이디를 입력해주세요'// required는 해당 field에 값이 없을 때 뜨는 에러임
    },
    'password': {
      'required' : '비밀번호를 입력해주세요'
    }
  };// 특정 에러에 대한 에러메세지를 미리 설정해놓는 부분

  // form을 생성하는 함수
  buildForm(): void{
    // form group을 생성하면서 그룹 안의 control을 생성하고 초기값과 validator_타입을 넣어줌
    this.form = this.formBuilder.group({
      userId: ["", Validators.required],
      password: ["", Validators.required],
    });

    // form값의 변화가 감지되면 updateFormErrors함수를 실행함
    this.form.valueChanges.subscribe(data => {
      this.loginUtilService.updateFormErrors(this.form, this.formErrors, this.formErrorMessages);
    });
  };

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private loginUtilService: LoginUtilService,
    private authService: AuthService,
  ) { 
    this.buildForm();// form생성
  }

  ngOnInit() {
  }

  // 제출 버튼을 눌렀을 때 호출되는 함수
  submit() {
    
    // form 에러업데이트
    this.loginUtilService.makeFormDirtyAndUpdateErrors(this.form, this.formErrors, this.formErrorMessages);
    if(this.form.valid){
      // login api호출
      this.authService.login(this.form.value.userId, this.form.value.password)
      // 로그인 성공시 redirect할 곳이 있으면 그곳으로, 아니면 /로 보내기
      .then(data =>{
        this.router.navigate([this.redirectTo?this.redirectTo:'/']);
      })
      // 로그인 실패시 함수를 실행하여 서버에러메세지를 업데이트함
      .catch(response =>{
        this.loginUtilService.handleFormSubmitError(response, this.form, this.formErrors);
      });
    }
  }

}
