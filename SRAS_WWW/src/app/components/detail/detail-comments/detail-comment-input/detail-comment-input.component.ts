import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { CommentsService } from '../../shared/comments.service';
import { stringify } from 'querystring';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detail-comment-input',
  templateUrl: './detail-comment-input.component.html',
  styleUrls: ['./detail-comment-input.component.css']
})
export class DetailCommentInputComponent implements OnInit {

  @Input()
  currentReviewId: string;

  @ViewChild('commentInputText') input: any;

  constructor(
    private commentsService: CommentsService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  postReviewComment(reviewId: string, userId: string, comment: string) {
    this.commentsService.postReviewComment(reviewId, userId, comment).then(result => {
      this.input.nativeElement.value = "";
      this.router.navigateByUrl('/blank', {
        skipLocationChange: true,
      })
        .then(
          () => {
            this.router.navigateByUrl(`/review/${this.currentReviewId}`);
          }
        )
    })
      .catch(() => {
        alert("코멘트 달기에 실패했습니다.");
      })
  }

  onClickRegisterCommentButton(event: Event) {
    let userId = localStorage.getItem('userId');
    this.postReviewComment(this.currentReviewId, userId, this.input.nativeElement.value);
  }

}
