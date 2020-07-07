import { Component, OnInit, Input } from '@angular/core';
import { ReviewAndReply } from 'app/components/shared/review-and-reply';

@Component({
  selector: 'app-detail-comments',
  templateUrl: './detail-comments.component.html',
  styleUrls: ['./detail-comments.component.css']
})
export class DetailCommentsComponent implements OnInit {

  @Input()
  currentReviewId: string;

  @Input()
  reviewAndReply: ReviewAndReply;
  
  constructor() { }

  ngOnInit() {
  }

}
