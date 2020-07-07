import { Component, OnInit, HostListener } from '@angular/core';
import { ReviewsService } from '../services/reviews.service';
import { IMyDrpOptions } from 'mydaterangepicker';
import { ReviewAndReply } from '../review-and-reply';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-review-card-list',
  templateUrl: './review-card-list.component.html',
  styleUrls: ['./review-card-list.component.css']
})
export class ReviewCardListComponent implements OnInit {

  reviewAndReplyCount: number;
  reviewAndReplyTotalCount: number;
  reviewAndReplyList: ReviewAndReply[] = [];

  isOpenOsFilter: boolean; // os 필터 열린 상태인지
  isOpenDateFilter: boolean; // date 필터 열린 상태인지
  isOpenStateFilter: boolean; // state 필터 열린 상태인지
  osFilterValue: string; // 현재 os값
  model: any = {
    beginDate: { year: 2000, month: 1, day: 1 },
    endDate: { year: 2000, month: 1, day: 1 }
  };
  isModelChanged: boolean; // date 필터 값 바뀌었는지
  stateFilterValue: string; // 현재 처리상태 값
  myDateRangePickerOptions: IMyDrpOptions = {
    width: "230px",
  };

  selectedTagContent: string[]; // 검색했을시 선택된 태그값들 저장
  searchMsg: string; // 검색 메세지 저장
  isSearchRes: boolean = false; // 검색 결과로 띄우는건지 아닌지

  constructor(
    private reviewsService: ReviewsService,
    private route: ActivatedRoute
  ) { 
    // 검색 페이지에서 review 페이지로 라우팅 했을 때 받아온 파람 데이터를 변수에 담아줌
    this.route.queryParams.subscribe(params => {
      this.selectedTagContent = params["selectedTagContent"];
      this.searchMsg = params["searchMsg"];
      console.log(this.searchMsg + " " + this.selectedTagContent);

      if(this.selectedTagContent == null && this.searchMsg == null){
        this.isSearchRes = false;
      }else{
        this.isSearchRes = true;
      }
    })
  }

  ngOnInit() {
    this.reviewAndReplyCount = 0;
    this.reviewAndReplyTotalCount = 3; // 현재 서버 목업데이터가 3개 뿐이므로 임시 설정해둠
    this.isOpenOsFilter = false;
    this.isOpenDateFilter = false;
    this.isOpenStateFilter = false;

    let today = new Date();
    this.model.beginDate.year = today.getFullYear();
    this.model.endDate.year = today.getFullYear();
    this.model.beginDate.month = today.getMonth() + 1;
    this.model.endDate.month = today.getMonth() + 1;
    this.model.beginDate.day = today.getDate();
    this.model.endDate.day = today.getDate(); // date picker 시작일자와 종료일자를 오늘로 맞춰줌

    this.getReviewCardList("all", "all", 0, 10, "", []);
  }

  getReviewCardList(osType: string, state: string, offset: number, itemsPerPage: number, searchText: string, tagList: string[]) {
    this.reviewsService.getReviewCardList(osType, state, offset, itemsPerPage, searchText, tagList).then(result => {
      this.reviewAndReplyList = result.data;
      // this.reviewAndReplyList = this.reviewAndReplyList.concat(result.data.reviewAndReply); // 나중에는 무한스크롤 되도록 concat할 것임
      this.reviewAndReplyCount += result.data.length;
      // this.reviewAndReplyTotalCount = result.data.totalCount; // 나중에는 무한스크롤 되도록 totalCount 받아올 것임
    })
    .catch(() => {
      alert("리뷰 목록 가져오기에 실패했습니다.")
    })
  }

  @HostListener("window:scroll", ["$event"])
  onWindowScroll(event: Event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.clientHeight;
    let max = document.documentElement.scrollHeight;
    if (pos == max && this.reviewAndReplyCount < this.reviewAndReplyTotalCount) {
      this.getReviewCardList(this.osFilterValue || (!this.osFilterValue && "all"),
        this.stateFilterValue || (!this.stateFilterValue && "all"), 0, 10, "", [""]);
    }
  } // 추후 서버 api 연동 시 동작하도록

  onClickOsFilter() {
    this.isOpenOsFilter = !this.isOpenOsFilter;
    if (this.isOpenDateFilter && this.isOpenOsFilter) { // date 필터 열린 상태에서 os 필터 클릭
      this.isOpenDateFilter = false; // date 필터 닫기
    }
    else if (this.isOpenStateFilter && this.isOpenOsFilter) { // state 필터 열린 상태에서 os 필터 클릭
      this.isOpenStateFilter = false; // state 필터 닫기
    }
  }

  onClickDateFilter() {
    this.isOpenDateFilter = !this.isOpenDateFilter;
    if (this.isOpenOsFilter && this.isOpenDateFilter) { // os 필터 열린 상태에서 date 필터 클릭
      this.isOpenOsFilter = false; // os 필터 닫기
    }
    else if (this.isOpenStateFilter && this.isOpenDateFilter) { // state 필터 열린 상태에서 date 필터 클릭
      this.isOpenStateFilter = false; // state 필터 닫기
    }
  }

  onClickStateFilter() {
    this.isOpenStateFilter = !this.isOpenStateFilter;
    if (this.isOpenOsFilter && this.isOpenStateFilter) { // os 필터 열린 상태에서 state 필터 클릭
      this.isOpenOsFilter = false; // os 필터 닫기
    }
    else if (this.isOpenDateFilter && this.isOpenStateFilter) { // date 필터 열린 상태에서 state 필터 클릭
      this.isOpenDateFilter = false; // date 필터 닫기
    }
  }

  onClickOsFilterOption(option: string) {
    if (this.osFilterValue !== option) { // 현재 옵션과 다른 옵션 클릭 시
      this.reviewAndReplyCount = 0;
      this.reviewAndReplyTotalCount = 3; // 현재 서버 목업데이터가 3개 뿐이므로 임시 설정해둠
      this.reviewAndReplyList = [];
      this.getReviewCardList(option, this.stateFilterValue ? this.stateFilterValue : "all", 0, 10, "", []);
    }
    this.osFilterValue = option;
    this.isOpenOsFilter = false; // date 필터 닫기
  }

  onClickStateFilterOption(option: string) {
    if (this.stateFilterValue !== option) { // 현재 옵션과 다른 옵션 클릭 시
      this.reviewAndReplyCount = 0;
      this.reviewAndReplyTotalCount = 3; // 현재 서버 목업데이터가 3개 뿐이므로 임시 설정해둠
      this.reviewAndReplyList = [];
      this.getReviewCardList(this.osFilterValue ? this.osFilterValue : "all", option, 0, 10, "", []);
    }
    this.stateFilterValue = option;
    this.isOpenStateFilter = false; // date 필터 닫기
  }

  onDateRangeChanged(event: Event) {
    this.isModelChanged = true;
    this.getReviewCardList("all", "all", 0, 10, "", ["temp"]); // TODO : date range query로 적용
  }

  getDateModelString(dateModel: any) {
    return dateModel.year + "." + dateModel.month + "." + dateModel.day;
  }
}
