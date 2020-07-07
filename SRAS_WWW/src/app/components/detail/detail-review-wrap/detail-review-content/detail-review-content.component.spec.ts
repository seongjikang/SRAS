import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailReviewContentComponent } from './detail-review-content.component';

describe('DetailReviewContentComponent', () => {
  let component: DetailReviewContentComponent;
  let fixture: ComponentFixture<DetailReviewContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailReviewContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailReviewContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
