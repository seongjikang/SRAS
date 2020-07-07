import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { Tag } from '../../shared/tag';

@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  selectedTag: Tag[] = [];// 선택되어 보여지는 태그목록
  
  constructor(
    private router : Router
  ) { }

  ngOnInit() { }

  @Output()
  tagDeleteEvent: EventEmitter<Tag> = new EventEmitter<Tag>();
  // 태그를 지울때마다 부모 컴포넌트로 그 이벤트를 전달해주는 역할

  @Input()
  set setClickedTag(tag: Tag){
    if(tag){
      if(tag.isClicked){
        this.selectedTag.push(tag);
      }
      else{
        let index:number = this.selectedTag.indexOf(tag);
        if(index !== -1) this.selectedTag.splice(index, 1);
      }
    }
  }

  onDeleteTag(event: Event, tag: Tag) {// 태그 삭제버튼 클릭 시 실행
    // selectedTag에서 삭제
    let index: number = this.selectedTag.indexOf(tag);
    if(index !== -1) this.selectedTag.splice(index, 1);

    this.tagDeleteEvent.emit(tag);// 부모 컴포넌트로 이벤트 바운드
  }

  // 검색 결과 제출했을 때, 파람과 함께 review 페이지로 네비게이트 
  submit(form: any): void{
    let selectedTagContent = [];// 선택된 태그의 text값만 담을 배열
    let extras: NavigationExtras = {
      queryParams: {
        "selectedTagContent" : selectedTagContent,
        "searchMsg" : form.searchMsg
      }
    }
    console.log(form); //form에 검색창에 들어있는 내용이 담겨서 옴

    for(let i of this.selectedTag){
      // 배열에 값 넣어주기
      selectedTagContent.push(i.tagContent);
      console.log(i.tagContent + " ");
    }

    this.router.navigate(['/', 'review'], extras);
  }
}
