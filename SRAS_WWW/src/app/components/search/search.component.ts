import { Component, OnInit } from '@angular/core';
import { Tag } from '../shared/tag';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  // 하위 컴포넌트로 속성을 바인딩 하기 위해 이벤트에서 받아온 태그 값을 저장할 변수들
  deletedTag: Tag;
  clickedTag: Tag;


  // 하위 컴포넌트에서 태그를 삭제했을 때 바인딩 된 이벤트
  tagDeleteTransmission(tag: Tag){
    this.deletedTag = tag;// 변수에 받아온 태그 값 저장
  }

  // 하위 컴포넌트에서 태그를 클릭했을 때 바인딩 된 이벤트
  onTagClicked(tag: Tag){
    this.clickedTag = tag;
  }

  constructor() { }

  ngOnInit() {
  }

}
