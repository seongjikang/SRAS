import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { TokenService } from './token.service';

@Injectable()
export class ReviewsService {

  constructor(
    private http: Http,
    private tokenService: TokenService 
  ) { }

  getReviewCardList(osType: string, state: string, offset: number, itemsPerPage: number, searchText: string, tagList: string[]): Promise<any> {

    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
<<<<<<< HEAD
=======
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());
>>>>>>> dc1c804b907353e9e6927efe4d1b0358043801a9

    let myParams = new URLSearchParams();
    myParams.set('offset', offset.toString());
    myParams.set('itemsPerPage', itemsPerPage.toString());
    myParams.set('searchText', searchText);
    myParams.set('tagList', this.getTagStringFromTagList(tagList));

    let options = new RequestOptions({ headers: myHeaders, search: myParams });

    return this.http.get(`${environment.apiBaseUrl}/reviews/get/list/osType/${osType}/state/${state}`, options).toPromise()
      .then((response: Response) => {
        return response.json();
      });
  }

  getTagStringFromTagList(tagList: string[]) {
    let result = "";
    for (let idx of tagList) {
      result += tagList[idx] + '|';
    }
    result.substring(0, result.length - 2); // 끝 한 칸 자름. 무조건 '|'이므로
    return result;
  }
}