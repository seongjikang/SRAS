package com.shinhan.sras.model;

public class RecommendReview {
	private String reviewDate;
	private String reviewId;
	private String isPositive;
	private String recommendReviewNo;
	private String recommendReviewContent;
	private String recommendReviewAnswer;

	public RecommendReview() {
	}

	public RecommendReview(String reviewDate, String reviewId, String isPositive, String recommendReviewNo, String recommendReviewContent, String recommendReviewAnswer) {
		this.reviewDate = reviewDate;
		this.reviewId = reviewId;
		this.isPositive = isPositive;
		this.recommendReviewNo = recommendReviewNo;
		this.recommendReviewContent = recommendReviewContent;
		this.recommendReviewAnswer = recommendReviewAnswer;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(String isPositive) {
		this.isPositive = isPositive;
	}

	public String getRecommendReviewNo() {
		return recommendReviewNo;
	}

	public void setRecommendReviewNo(String recommendReviewNo) {
		this.recommendReviewNo = recommendReviewNo;
	}

	public String getRecommendReviewContent() {
		return recommendReviewContent;
	}

	public void setRecommendReviewContent(String recommendReviewContent) {
		this.recommendReviewContent = recommendReviewContent;
	}

	public String getRecommendReviewAnswer() {
		return recommendReviewAnswer;
	}

	public void setRecommendReviewAnswer(String recommendReviewAnswer) {
		this.recommendReviewAnswer = recommendReviewAnswer;
	}
}
