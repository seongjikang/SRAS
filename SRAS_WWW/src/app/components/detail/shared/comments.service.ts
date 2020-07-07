import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { TokenService } from 'app/components/shared/services/token.service';

@Injectable()
export class CommentsService {

  constructor(
    private http: Http,
    private tokenService: TokenService
  ) { }

  getReviewComments(reviewId: string): Promise<any> {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());

    let options = new RequestOptions({ headers: myHeaders });

    return this.http.get(`${environment.apiBaseUrl}/comments/get/list/reviewId/${reviewId}`, options).toPromise()
      .then((response: Response) => {
        return response.json();
      })
  }

  postReviewComment(reviewId: string, userId: string, comment: string) {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());

    let options = new RequestOptions({ headers: myHeaders });
    
    return this.http.post(`${environment.apiBaseUrl}/comments/register/reviewId/${reviewId}`,
      { "userId": userId, "comment": comment }, options).toPromise()
      .then((response: Response) => {
        return response.json();
      })
  }

  putApproveChk(reviewId: string, isRejected: boolean, reason: string) {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    // TODO : 토큰 없을 때 에러 처리할 로직 작성
    myHeaders.set('Authorization', 'Bearer ' + this.tokenService.getToken());

    let options = new RequestOptions({ headers: myHeaders });

    return this.http.put(`${environment.apiBaseUrl}/replies/approve/reviewId/${reviewId}`,
      { "approveChk": isRejected ? "reject" : "complete", "reason": reason }, options).toPromise()
      .then((response: Response) => {
        return response.json();
      })
  }
}