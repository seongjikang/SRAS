import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Tag } from '../../shared/tag';

@Component({
  selector: 'app-tag-list',
  templateUrl: './tag-list.component.html',
  styleUrls: ['./tag-list.component.css']
})
export class TagListComponent implements OnInit {

  tagList: Tag[] = [
    // (태그내용, 클릭됐는지, hover됐는지)
    new Tag("모바일OTP", false),
    new Tag("바이오인증", false),
    new Tag("공인인증서", false),
    new Tag("계좌개설", false),
    new Tag("인증번호", false),
    new Tag("접속", false)
  ]; // 태그 정보 목업 데이터
  // TODO : 추후 detail-review-wrap 의 ngOnInit에서 review 정보 get api 연동할 예정

  constructor() { }

  ngOnInit() {
  }

  @Output()
  tagClickedEvent: EventEmitter<Tag> = new EventEmitter<Tag>();
  // 태그를 클릭했을 때 일어나는 이벤트를 부모 컴포넌트로 전달하는 역할

  @Input() 
  set setDeletedTag(tag: Tag){
    if(tag){// 처음에 undefind가 들어가 있어 없는 값에 이벤트 처리를 해주었을 때 나는 오류 방지
      tag.isClicked = false;// 태그 선택이 해제 되었으므로 isClicked를 false로 바꿔줌
    }
  }

  onClickTag(event: Event, tag: Tag) {// 태그 클릭 시 실행
    tag.isClicked = !tag.isClicked;// 태그 클릭 상태 업데이트
    this.tagClickedEvent.emit(tag);// 부모 컴포넌트로 클릭 이벤트 바인딩
  }
  
}
