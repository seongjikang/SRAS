import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { environment } from '../../../../environments/environment';

@Injectable()
export class LoginUtilService {

  // api 호출 성공여부 가져오기
  public checkSuccess(response: any): Promise<any> {
    let apiResponse = response.json();

    if(apiResponse.success) return Promise.resolve(apiResponse);// 성공했으면 이행된 promise 반환하기
    else return Promise.reject(apiResponse);// 실패했으면 거부된 promise 반환하기
  }

  // api에 error가 있는 경우 브라우저에 error 표시
  public handleApiError(error: any): Promise<any> {
    if(!environment.production) console.error('에러 발생', error);
    return Promise.reject(error);
  }

  // form의 에러상태를 확인하고 에러를 가져와서 formErrors에 에러 메세지를 넣어줌
  public updateFormErrors(form: FormGroup, formErrors: any, formErrorMessages: any) {
    if (!form) { 
      return;
    }// form값이 비어있으면 그냥 리턴

    for (const field in formErrors) {
      formErrors[field] = '';// 에러메세지를 담는 변수 초기화
      const control = form.get(field);// form field의 control받아오기
      if (control && control.dirty && !control.valid) {// control이 빈값이 아니고, 변경이 되었으며 값이 유효하지 않을 때 에러처리
        const messages = formErrorMessages[field];// 에러메세지에 담겨있는내용 받아오기
        if(messages){// 에러메세지가 있으면
          for (const key in control.errors) {// 해당 에러메세지 담아주기
            formErrors[field] += messages[key] + ' ';
          }
        }
      }
    }
  }

  // 항목 값의 변화 없이도 form을 dirty하게 만드는 함수
  public makeAllFormFieldsDirty(form: FormGroup) {
    if (!form) { 
      return; 
    }// form값이 비어있으면 그냥 리턴

    for (var field in form.controls) {
      const control = form.get(field);
      if(control) control.markAsDirty();
    }
  }

  // form을 제출하기 전에 form을 dirty하게 만들고 에러를 업데이트하는 함수
  public makeFormDirtyAndUpdateErrors(form: FormGroup, formErrors: any, formErrorMessages: any) {
    this.makeAllFormFieldsDirty(form);
    this.updateFormErrors(form, formErrors, formErrorMessages);
  }

  // TODO: msg에 오류 메세지가 어떤식으로 담겨오는지 확인 후 로직 짜기, #37
  public handleFormSubmitError(response: any, form: FormGroup, formErrors: any): void {
    let apiResponse = response.json();
  }

  constructor() { }

}
