import { Component, OnInit, HostListener, Input } from '@angular/core';
import { DetailComment } from './detail-comment-item/detail-comment';
import { CommentsService } from '../../shared/comments.service';

@Component({
  selector: 'app-detail-comment-list',
  templateUrl: './detail-comment-list.component.html',
  styleUrls: ['./detail-comment-list.component.css']
})
export class DetailCommentListComponent implements OnInit {

  @Input()
  currentReviewId: string;
  
  comments: DetailComment[] = [];
  commentCount: number;
  commentTotalCount: number;

  constructor(
    private commentsService: CommentsService
  ) { }

  ngOnInit() {
    this.commentCount = 0;
    this.commentTotalCount = 2; // TODO : 추후 무한스크롤 작동 시 수정 예정
    this.getReviewComments(this.currentReviewId);
  }

  getReviewComments(reviewId: string) {
    this.commentsService.getReviewComments(reviewId).then(result => {
      this.comments = this.comments.concat(result.data);
      this.commentCount += result.data.length;
      // this.commentTotalCount = result.dataBody.totalCount; // TODO : 추후 무한스크롤 작동 시 주석 해제 예정
    })
    .catch(() => {
      alert("코멘트 가져오기에 실패했습니다.");
    })
  }

  // @HostListener("window:scroll", ["$event"])
  // onWindowScroll() {
  //   let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.clientHeight;
  //   let max = document.documentElement.scrollHeight;
  //   if(pos == max && this.commentCount < this.commentTotalCount)   {
  //     this.getReviewComments(this.currentReviewId);
  //   }
  // } // TODO : 추후 서버 api 연동 시 동작하도록

}
