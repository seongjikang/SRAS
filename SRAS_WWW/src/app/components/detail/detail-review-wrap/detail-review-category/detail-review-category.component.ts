import { Component, OnInit, Output, EventEmitter, Input, ViewChild, SimpleChanges } from '@angular/core';
import { Tag } from 'app/components/shared/tag';
import { TagService } from '../../../shared/services/tag.service';
import { ReviewAndReply } from 'app/components/shared/review-and-reply';

@Component({
  selector: 'app-detail-review-category',
  templateUrl: './detail-review-category.component.html',
  styleUrls: ['./detail-review-category.component.css']
})
export class DetailReviewCategoryComponent implements OnInit {

  @Input()
  isNegative: boolean; // 부정리뷰인지

  @Input()
  isSubmitPorN: boolean; // 긍정/부정 아래 확인버튼 클릭여부

  @Input()
  reviewAndReply: ReviewAndReply; // 서버에서 받아온 답글 정보

  chosenTags: Tag[] = []; // 선택되는 태그 저장
  tagList: Tag[] = [];

  @ViewChild('tagToAdd') input: any;

  @Output()
  submitPorNClickEvent: EventEmitter<boolean> = new EventEmitter<boolean>();
  // 긍정/부정 아래 확인버튼 클릭해서 해당 정보를 reply에 전달하는 역할

  @Output()
  tagChooseEvent: EventEmitter<Tag[]> = new EventEmitter<Tag[]>();
  // 태그를 선택할 때마다 chosenTags를 wrap으로 전달하는 역할

  isFinishedGetTagList: boolean;
  isFinishedGetReviewAndReply: boolean;

  constructor(
    private tagService: TagService
  ) { }

  ngOnInit() {
    this.isNegative = false;
    this.isSubmitPorN = false;
    this.isFinishedGetTagList = false;
    this.isFinishedGetReviewAndReply = false;
    this.getTagList();
  }

  getTagList() {
    setTimeout(() => {
      this.tagService.getTagList().then(result => {
        //this.tagList = this.getTagsFromTagString(result.data.tags);
        this.tagList = result.data;
        this.isFinishedGetTagList = true;

        if (this.isFinishedGetReviewAndReply) {

          this.chosenTags = this.getTagsFromTagString(this.reviewAndReply.tags); // 서버에 저장된 선택된태그를 chosenTags 변수에 저장

          this.tagList = this.tagList.map(tag => {
            for (let chosenTag of this.chosenTags) {
              if (tag.tagContent == chosenTag.tagContent) {
                return { "tagContent": tag.tagContent, "isClicked": true }; // chosenTag이면 isClicked true로
              }
            }
            return { "tagContent": tag.tagContent, "isClicked": false }; // chosenTag 아니면 isClicked false
          });
        }
      })
      .catch(() => {
        alert("태그 목록 가져오기에 실패했습니다.")
      })
    }, 1000);

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

  postNewTag(tagContent: string) {
    this.tagService.postNewTag(tagContent).then(result => {
      this.tagList = this.tagList.concat({ "tagContent": tagContent, "isClicked": false });
      this.input.nativeElement.value = "";
    })
  }

  onClickNegativeToggle(event: Event) { // 긍정/부정 토글 클릭 시 실행
    this.isNegative = !this.isNegative;
  }

  onClickButtonToFixPorN(event: Event) { // 긍정/부정 아래 확인 버튼 클릭 시 실행
    this.isSubmitPorN = true;
    this.submitPorNClickEvent.emit(this.isNegative); // wrap으로 이벤트 방출
  }

  onClickTag(event: Event, tag: Tag) { // 태그 클릭 시 실행
    tag.isClicked = !tag.isClicked;

    if (tag.isClicked) { // 클릭됐을 때 chosenTags에 추가
      this.chosenTags = this.chosenTags.concat(tag);
    }
    else if (!tag.isClicked) { // 클릭 해제됐을 때 chosenTags에서 제거
      let index: number = this.chosenTags.indexOf(tag);
      if (index !== -1) this.chosenTags.splice(index, 1);
    }
    this.tagChooseEvent.emit(this.chosenTags); // wrap으로 이벤트 방출
  }

  onClickAddTagButton(event: Event) {
    this.postNewTag(this.input.nativeElement.value);
  }

  ngOnChanges(changes: SimpleChanges) {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)) {
        if (propName === "reviewAndReply" && changes["reviewAndReply"].currentValue.replyContent) { // reply 내용 서버에 저장되어 있으면
          this.isFinishedGetReviewAndReply = true;
          this.isNegative = this.reviewAndReply.replyContent ? true : false;
          this.isSubmitPorN = true;
          this.chosenTags = this.getTagsFromTagString(this.reviewAndReply.tags);
          this.tagChooseEvent.emit(this.chosenTags);
          this.submitPorNClickEvent.emit(this.isNegative); // 답글 만들었을 때와 같은 처리 해줌
        }
      }
    }
  }
}
