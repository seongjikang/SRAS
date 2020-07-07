import { Component, OnInit, Input } from '@angular/core';
import { ReviewAndReply } from 'app/components/shared/review-and-reply';

@Component({
  selector: 'app-detail-review-content',
  templateUrl: './detail-review-content.component.html',
  styleUrls: ['./detail-review-content.component.css']
})
export class DetailReviewContentComponent implements OnInit {

  @Input()
  reviewAndReply: ReviewAndReply

  constructor() { }

  ngOnInit() {
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

  getDateString(date: string) {
    return date[4] + date[5] // 월
      + '.' + date[6] + date[7]; // 일
  }

}
