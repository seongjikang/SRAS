import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Subscription } from 'rxjs/Subscription';
import { ReviewAndReply } from '../shared/review-and-reply';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  private routeSub: Subscription;

  currentReviewId: string;
  reviewAndReply: ReviewAndReply;

  constructor(
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.routeSub = this.activatedRoute.params.subscribe(params => {
      this.currentReviewId = params['reviewId'];
    });
  }
  receiveReviewAndReply(reviewAndReply: ReviewAndReply)  {
    this.reviewAndReply = reviewAndReply;
  }
}
