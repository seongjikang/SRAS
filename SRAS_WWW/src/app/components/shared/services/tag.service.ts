import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../../../environments/environment';
<<<<<<< HEAD
=======
import { TokenService } from './token.service';
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9

@Injectable()
export class TagService {

  constructor(
<<<<<<< HEAD
    private http: Http
=======
    private http: Http,
    private tokenService: TokenService
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9
  ) { }

  getTagList(): Promise<any> {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
<<<<<<< HEAD
    // TODO : Authorization 에 토큰 삽입
=======
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9

    let options = new RequestOptions({ headers: myHeaders });

    return this.http.get(`${environment.apiBaseUrl}/tags/get/all/tag`, options).toPromise()
<<<<<<< HEAD
            .then((response: Response) => {
              return response.json();
            })
=======
      .then((response: Response) => {
        return response.json();
      })
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9
  }

  postNewTag(tagContent: string) {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
<<<<<<< HEAD
    // TODO : Authorization 에 토큰 삽입

    let options = new RequestOptions({ headers: myHeaders });

    return this.http.post(`${environment.apiBaseUrl}/tags/register`, {"tagContent": tagContent}, options).toPromise()
            .then((response: Response) => {
              return response.json();
            })
=======
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());

    let options = new RequestOptions({ headers: myHeaders });

    return this.http.post(`${environment.apiBaseUrl}/tags/register`, { "tagContent": tagContent }, options).toPromise()
      .then((response: Response) => {
        return response.json();
      })
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9
  }
}