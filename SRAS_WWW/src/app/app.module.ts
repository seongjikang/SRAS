import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MyDateRangePickerModule } from 'mydaterangepicker';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { SearchComponent } from './components/search/search.component';
import { DetailComponent } from './components/detail/detail.component';
import { DetailReviewWrapComponent } from './components/detail/detail-review-wrap/detail-review-wrap.component';
import { DetailCommentsComponent } from './components/detail/detail-comments/detail-comments.component';
import { DetailRejectComponent } from './components/detail/detail-comments/detail-reject/detail-reject.component';
import { SearchPanelComponent } from './components/search/search-panel/search-panel.component';
import { TagListComponent } from './components/search/tag-list/tag-list.component';
import { KeywordRecommendComponent } from './components/search/keyword-recommend/keyword-recommend.component';
import { ReviewCardListComponent } from './components/shared/review-card-list/review-card-list.component';
import { ReviewCardItemComponent } from './components/shared/review-card-list/review-card-item/review-card-item.component';
import { DetailReviewContentComponent } from './components/detail/detail-review-wrap/detail-review-content/detail-review-content.component';
import { DetailReviewCategoryComponent } from './components/detail/detail-review-wrap/detail-review-category/detail-review-category.component';
import { DetailReviewReplyComponent } from './components/detail/detail-review-wrap/detail-review-reply/detail-review-reply.component';
import { DetailCommentListComponent } from './components/detail/detail-comments/detail-comment-list/detail-comment-list.component';
import { DetailCommentItemComponent } from './components/detail/detail-comments/detail-comment-list/detail-comment-item/detail-comment-item.component';
import { DetailCommentInputComponent } from './components/detail/detail-comments/detail-comment-input/detail-comment-input.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { LoginUtilService } from './components/login/shared/login-util.service';
import { AuthService } from './components/login/shared/auth.service';
import { ReviewDetailService } from './components/detail/shared/review-detail.service';
import { CommentsService } from './components/detail/shared/comments.service';
import { TokenService } from './components/shared/services/token.service';
import { ReviewsService } from './components/shared/services/reviews.service';
import { AuthGuard } from './components/shared/auth.guard';
import { TagService } from './components/shared/services/tag.service';
import { BlankComponent } from './components/blank/blank.component';
import { NotYetComponent } from './components/not-yet/not-yet.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    MainComponent,
    SearchComponent,
    DetailComponent,
    DetailReviewWrapComponent,
    DetailCommentsComponent,
    DetailRejectComponent,
    SearchPanelComponent,
    TagListComponent,
    KeywordRecommendComponent,
    ReviewCardListComponent,
    ReviewCardItemComponent,
    DetailReviewContentComponent,
    DetailReviewCategoryComponent,
    DetailReviewReplyComponent,
    DetailCommentListComponent,
    DetailCommentItemComponent,
    DetailCommentInputComponent,
    NotYetComponent,
    BlankComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MyDateRangePickerModule
  ],
  providers: [
    AuthGuard,
    ReviewDetailService,
    CommentsService,
    ReviewsService,
    LoginUtilService,
    TagService,
    AuthService,
    TokenService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
