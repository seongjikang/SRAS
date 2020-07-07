import { Component, OnInit, Input } from '@angular/core';
import { DetailComment } from './detail-comment';

@Component({
  selector: 'app-detail-comment-item',
  templateUrl: './detail-comment-item.component.html',
  styleUrls: ['./detail-comment-item.component.css']
})
export class DetailCommentItemComponent implements OnInit {

  @Input()
  comment: DetailComment;

  constructor() { }

  ngOnInit() {
  }

  getDateString(date: string) {
    return date[0] + date[1] + date[2] + date[3] // 연도
          + '.' + date[4] + date[5] // 월
          + '.' + date[6] + date[7]; // 일
  }

}
