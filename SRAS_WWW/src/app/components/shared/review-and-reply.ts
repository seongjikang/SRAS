export class ReviewAndReply {
    constructor(
        public reviewId: string, // 리뷰 id
        public reviewContent: string, // 리뷰 내용
        public osType: string, // android or ios
        public osVersion: string, // os 버전
        public reviewStar: string, // 별점
        public reviewDate: string, // 작성일자
        public deviceInfo: string, // 기기
        public maker: string, // 기기 제조사
        public appVersion: string, // 앱 버전
        public buildNo: string, // 빌드번호
        public replyContent: string, // 답글 내용
        public replyManagerId: string, // 답글 담당자 행번
        public replyManagerName: string, // 답글 담당자 이름
        public requestDate: string, // 승인 요청 날짜 (현재 화면에 나타나지 않음)
        public approveDate: string, // 승인 날짜
        public approveChk: string, // 결재상태
        public tags: string, // 파이프 형태의 태그리스트
        public reason: string // 반려사유
    ) { }
}