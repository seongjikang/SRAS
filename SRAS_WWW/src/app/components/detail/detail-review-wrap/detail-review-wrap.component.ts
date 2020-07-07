import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Tag } from 'app/components/shared/tag';
import { ReviewAndReply } from '../../shared/review-and-reply';
import { ReviewDetailService } from '../shared/review-detail.service';
import { Router } from '@angular/router';
import { CommentsService } from '../shared/comments.service';

@Component({
  selector: 'app-detail-review-wrap',
  templateUrl: './detail-review-wrap.component.html',
  styleUrls: ['./detail-review-wrap.component.css']
})
export class DetailReviewWrapComponent implements OnInit {

  @Input()
  currentReviewId: string;

  reviewAndReply: ReviewAndReply;
  
  @Output()
  reviewAndReplySendEvent: EventEmitter<ReviewAndReply> = new EventEmitter<ReviewAndReply>();

  // 긍정/부정 아래 확인버튼 클릭해서 해당 정보를 reply에 전달하는 역할
  isSubmitPorN: boolean;
  isNegative: boolean;
  chosenTags: Tag[]; // 선택된 태그 리스트
  tagCount: number; // chosenTags 개수 세서 app-detail-review-reply로 넘겨줌
  reply: string; // 서버에서 받아올 답글 내용
  category: number; // 서버에서 받아올 선택된 카테고리
  detailCategory: number; // 서버에서 받아올 선택된 세부카테고리
  replyContentToSave: string; // Save 버튼 눌렀을 때 전송될 답글 내용
  existReplyContentToSave: boolean; // 답글 내용 작성했는지 여부 저장

  constructor(
    private reviewDetailService: ReviewDetailService,
    private commentsService: CommentsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.chosenTags = [];
    this.tagCount = 0;
    this.isNegative = false;
    this.isSubmitPorN = false;

    let userId = localStorage.getItem('userId');
    this.getReviewAndReply(userId);
  }

  getReviewAndReply(userId: string) {
    this.reviewDetailService.getReviewAndReply(userId, this.currentReviewId).then(result => {
      this.reviewAndReply = result.data;
      if (this.reviewAndReply.replyContent) {
        this.reply = this.reviewAndReply.replyContent;
      }
      this.reviewAndReplySendEvent.emit(this.reviewAndReply);
      
    });
    // TODO : 추후 tempReplyInfo.replyManagerId로 이름 받아오는 api 생기는 대로 연동할 예정
  }

  postReplyContent(managerId: string, reviewId: string, replyContent: string, tagList: Tag[]) {
    this.reviewDetailService.postReplyContent(managerId, reviewId, replyContent, tagList).then(result => {
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
        alert("답글 작성에 실패했습니다.");
    })
  }

  putReplyReject(reviewId: string, isRejected: boolean, reason: string) {
    this.commentsService.putApproveChk(reviewId, isRejected, reason)
    .then(result => {
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
      alert("반려 사유를 등록할 수 없습니다.");
    })
  }

  onClickConfirmButton() {
    this.putReplyReject(this.currentReviewId, false, "");
  }

  openReplyInput(isNegative: boolean) {
    this.isNegative = isNegative;
    this.isSubmitPorN = true;
    // 부정리뷰면 답글 쓸 수 있음, 긍정/부정은 app-detail-review-category에서 보내줌
  }

  addChosenTags(tags: Tag[]) {
    this.chosenTags = tags; // 선택된 태그 리스트를 app-detail-review-category에서 받아옴
    this.tagCount = this.chosenTags.length;
  }

  getReplyContentToSave(replyContentToSave: string) {
    this.replyContentToSave = replyContentToSave;
  }

  existReplyContent(exists: boolean) {
    this.existReplyContentToSave = exists;
  }

  onClickBackButton() {
    this.isSubmitPorN = false;
  }

  onClickSaveButton() {
    let userId = localStorage.getItem('userId');
    this.postReplyContent(userId, this.currentReviewId, this.replyContentToSave, this.chosenTags);
  }
 
}
