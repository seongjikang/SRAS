

import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-detail-review-reply',
  templateUrl: './detail-review-reply.component.html',
  styleUrls: ['./detail-review-reply.component.css']
})
export class DetailReviewReplyComponent implements OnInit {

  @Input()
  isNegative: boolean; // 답글을 쓸 수 있는지 여부를 부모인 wrap으로부터 받아옴
  @Input()
  isSubmitPorN: boolean; // 긍정/부정 확인버튼 눌렀는지 여부를 부모인 wrap으로부터 받아옴
  @Input()
  chosenTagCount: number; // 선택된 태그의 개수를 부모인 wrap으로부터 받아옴
  replyContent: string[][];
  @Input()
  savedReply: string;
  @Input()
  category: number;
  @Input()
  detailCategory: number[];
  frontTemplate: string[][] = [
    [
      "안녕하세요. 신한은행입니다. \nSOL 이용에 불편을 드려 죄송합니다.\n", // 0번 카테고리(에러발생) 1번 템플릿
      "안녕하세요. 신한은행입니다. \nSOL 이용에 불편을 드려 죄송합니다.\n" // 0번 카테고리(에러발생) 2번 템플릿
    ],
    [
      "안녕하세요. 신한은행입니다. \n고객님의 소중한 의견 감사합니다. 담당부서에 전달하여 서비스 개선에 반영하도록 하겠습니다.", // 1번 카테고리(개선사항) 1번 템플릿
      "안녕하세요. 신한은행입니다. \n말씀해주신 기능은 SOL에 포함되어 있으니 아래 경로를 참고하여 주시기 바랍니다." // 1번 카테고리(개선사항) 2번 템플릿
    ],
    ["안녕하세요. 신한은행입니다. 불편을 드려 정말 죄송합니다. "], // 2번 카테고리(단순불만)
    [""] // 3번 카테고리(자유양식)
  ];
  existCenterTextarea: boolean[][] = [ // 텍스트 영역 존재 여부
    [
      true,
      false
    ],
    [
      false,
      true
    ],
    [true],
    [true]
  ];
  backTemplate: string[][] = [
    [
      "위 방법으로 문제가 해결되지 않는 경우 다음 정보를 고객센터(1599-8000) 또는 이메일(developer@shinhan.com)를 통해 전달해주시면 확인후 답변드리겠습니다. **(기종, OS정보, 고객번호, 휴대폰번호, 오류화면 캡처)\n\n고객님께 더 나은 서비스를 제공할 수 있도록 노력하겠습니다.\n감사합니다.", // 0번 카테고리(에러발생) 1번 템플릿
      "오류 진단을 위해 보다 자세한 정보가 필요합니다." // 0번 카테고리(에러발생) 2번 템플릿
    ],
    [
      "고객님께 더 나은 서비스를 제공할 수 있도록 노력하겠습니다.\n감사합니다.", // 1번 카테고리(개선사항) 1번 템플릿
      "고객님께 더 나은 서비스를 제공할 수 있도록 노력하겠습니다.\n감사합니다." // 1번 카테고리(개선사항) 2번 템플릿
    ],
    ["항상 좋은 서비스를 제공하는 신한은행이 될 수 있도록 노력하겠습니다\n감사합니다."], // 2번 카테고리(단순불만)
    [""] // 3번 카테고리(자유양식)
  ];

  @Output()
  existReplyContentEvent: EventEmitter<boolean> = new EventEmitter<boolean>();
  // 답글 내용이 존재하는지 여부를 wrap에 알려줌

  @Output()
  modifyReplyContentEvent: EventEmitter<string> = new EventEmitter<string>();
  // 답글 내용 작성 시 발생하는 이벤트. 서버로 보낼 답글 내용을 wrap으로 전달함

  constructor() { }

  ngOnInit() {
    this.replyContent = [["", ""], ["", ""], [""], [""]]; // 템플릿별 답글 작성 중이던 내용 리셋
    this.category = 0; // 카테고리 첫번째 것으로 리셋
    this.detailCategory = [1, 1, 1, 1]; // 세부 카테고리 모두 첫번째 것으로 리셋
    this.isNegative = false;

    if (this.savedReply) {
      this.category = 3;
      this.replyContent[this.category][this.detailCategory[this.category] - 1] = this.savedReply;
      this.onChangeReplyContent();
    }
  }

  onClickCategory(category: number) { // 카테고리 클릭 시 category 변수에 해당값 저장
    this.category = category;
    this.onChangeReplyContent();
  }

  onClickDetailCategory(detailCategory: number) { // 세부 카테고리 클릭 시 detailCategory 해당 인덱스에 저장
    this.detailCategory[this.category] = detailCategory;
    this.onChangeReplyContent();
  }

  onChangeReplyContent() {
    let replyContentToSave = this.frontTemplate[this.category][this.detailCategory[this.category] - 1]
      + "" + this.replyContent[this.category][this.detailCategory[this.category] - 1]
      + "\n" + this.backTemplate[this.category][this.detailCategory[this.category] - 1];

    if (this.replyContent[this.category][this.detailCategory[this.category] - 1] // 답글 작성 내용 존재하면
      || this.existCenterTextarea[this.category][this.detailCategory[this.category] - 1] == false) { // 또는 답글칸 없는 템플릿인 경우 
      this.existReplyContentEvent.emit(true);
      this.modifyReplyContentEvent.emit(replyContentToSave);
    }
    else {
      this.existReplyContentEvent.emit(false);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)) {
        if (propName === "savedReply" && changes["savedReply"].currentValue !== undefined) { // replyInfo 값 서버에서 받으면
          this.category = 3;
          this.replyContent[this.category][this.detailCategory[this.category] - 1] = this.savedReply;
        }
      }
    }
  }

}
