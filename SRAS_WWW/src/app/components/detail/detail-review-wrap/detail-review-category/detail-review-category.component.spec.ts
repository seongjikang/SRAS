import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailReviewCategoryComponent } from './detail-review-category.component';

describe('DetailReviewCategoryComponent', () => {
  let component: DetailReviewCategoryComponent;
  let fixture: ComponentFixture<DetailReviewCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailReviewCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailReviewCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
