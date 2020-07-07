import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { CommentsService } from '../../shared/comments.service';
import { ReviewAndReply } from 'app/components/shared/review-and-reply';

@Component({
  selector: 'app-detail-reject',
  templateUrl: './detail-reject.component.html',
  styleUrls: ['./detail-reject.component.css']
})
export class DetailRejectComponent implements OnInit {

  @Input()
  currentReviewId: string;

  @Input()
  reviewAndReply: ReviewAndReply;

  @ViewChild('commentSectionTextarea') input: any;

  constructor(
    private router: Router,
    private commentsService: CommentsService
  ) { }

  ngOnInit() {
  }

  putReplyReject(reviewId: string, isRejected: boolean, reason: string) {
    this.commentsService.putApproveChk(reviewId, isRejected, reason).then(result => {
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
      alert("반려 사유 적용에 실패했습니다.");
    })
  }

  onClickRejectButton() {
    this.putReplyReject(this.currentReviewId, true, this.input.nativeElement.value);
  }
}