import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { Tag } from 'app/components/shared/tag';
import { TokenService } from 'app/components/shared/services/token.service';

@Injectable()
export class ReviewDetailService {

  constructor(
    private http: Http,
    private tokenService: TokenService
  ) { }

  getReviewAndReply(userId: string, reviewId: string): Promise<any> {

    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());    
    
    let options = new RequestOptions({ headers: myHeaders });

    return this.http.get(`${environment.apiBaseUrl}/reviews/detail/info/userId/${userId}/reviewId/${reviewId}`, options).toPromise()
            .then((response: Response) => {
              return response.json();
            })
  }

  postReplyContent(managerId: string, reviewId: string, replyContent: string, tagList: Tag[]) {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());
    
    let options = new RequestOptions({ headers: myHeaders });

    return this.http.put(`${environment.apiBaseUrl}/replies/modify/managerId/${managerId}/reviewId/${reviewId}`,
                { "replyContent": replyContent, "tagList": this.getTagStringFromTagList(tagList) }, options).toPromise()
            .then((response: Response) => {
              return response.json();
            })
  }

  putReplyContent(managerId: string, reviewId: string, replyContent: string, tagList: Tag[]) {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());
    
    let options = new RequestOptions({ headers: myHeaders });

    return this.http.put(`${environment.apiBaseUrl}/replies/register/managerId/${managerId}/reviewId/${reviewId}`,
                { "replyContent": replyContent, "tagList": this.getTagStringFromTagList(tagList) }, options).toPromise()
            .then((response: Response) => {
              return response.json();
            })
  }

  getTagStringFromTagList(tagList: Tag[]) {
    let result = "";
    let index = 0;
    for (let tag of tagList) {
      result += tag.tagContent;
      if (tagList.length - 1 !== index) {
        result += "|";
      }
      index += 1;
    }
    return result;
  }
}