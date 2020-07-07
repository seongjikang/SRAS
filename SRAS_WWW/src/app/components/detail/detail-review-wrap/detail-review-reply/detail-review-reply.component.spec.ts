import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailReviewReplyComponent } from './detail-review-reply.component';

describe('DetailReviewReplyComponent', () => {
  let component: DetailReviewReplyComponent;
  let fixture: ComponentFixture<DetailReviewReplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailReviewReplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailReviewReplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
