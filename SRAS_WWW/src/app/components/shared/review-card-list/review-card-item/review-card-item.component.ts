import { Component, OnInit, Input, SimpleChanges } from '@angular/core';
import { ReviewAndReply } from '../../review-and-reply';
import { Router } from '@angular/router';
import { Tag } from '../../tag';

@Component({
  selector: 'app-review-card-item',
  templateUrl: './review-card-item.component.html',
  styleUrls: ['./review-card-item.component.css']
})
export class ReviewCardItemComponent implements OnInit {

  @Input()
  reviewAndReply: ReviewAndReply;
  
  tagList: Tag[];

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.tagList = this.getTagsFromTagString(this.reviewAndReply.tags ? this.reviewAndReply.tags: "");
   }

  onClickReviewCardItem(reviewId: string) {
    this.router.navigate(['/review/', reviewId]);
  }

  getTagsFromTagString(tagString: string) {
    let tags: Tag[] = [];
    let tempTag = "";
    for (let idx = 0; idx < tagString.length; idx++) {
      if (tagString[idx] == '|') {
        tags = tags.concat({ "tagContent": tempTag, "isClicked": false });
        tempTag = "";
      } else if (idx == tagString.length - 1) {
        tempTag += tagString[idx];
        tags = tags.concat({ "tagContent": tempTag, "isClicked": false });
        tempTag = "";
      } else {
        tempTag += tagString[idx];
      }
    }
    return tags;
  }

  getReviewDateString(reviewDate: string) {
    return reviewDate[0] + reviewDate[1] + reviewDate[2] + reviewDate[3] // 연도
          + '.' + reviewDate[4] + reviewDate[5] // 월
          + '.' + reviewDate[6] + reviewDate[7]; // 일
  }

  getHangulStateString(state: string) {
    if (state === "complete") {
      return "완료";
    }
    else if (state === "unprocess") {
      return "미처리";
    }
    else if (state === "wait") {
      return "승인대기";
    }
    else if (state === "reject") {
      return "반려";
    }
    return "-";
  }

  postApproveChk() {
    // TODO : 유승용 수석님이 승인하실 때 그 내용 post로 쏠 부분. reviewId 추후 parameter로 넣을 것
    this.router.navigateByUrl('/blank', {
      skipLocationChange: true,
    })
    .then(
      () => {
        this.router.navigateByUrl(`/review/${this.reviewAndReply.reviewId}`);
      }
    )  }

}
