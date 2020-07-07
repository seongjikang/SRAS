import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailReviewWrapComponent } from './detail-review-wrap.component';

describe('DetailReviewWrapComponent', () => {
  let component: DetailReviewWrapComponent;
  let fixture: ComponentFixture<DetailReviewWrapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailReviewWrapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailReviewWrapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
