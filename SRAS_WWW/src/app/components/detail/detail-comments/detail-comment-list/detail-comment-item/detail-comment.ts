export class DetailComment {
    constructor(
        public commentId: string,
        public reviewId: string,
        public userId: string,
        public userName: string,
        public comment: string,
        public commentDate: string
    ) { }
}
