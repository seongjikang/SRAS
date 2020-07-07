import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewCardListComponent } from './review-card-list.component';

describe('ReviewCardListComponent', () => {
  let component: ReviewCardListComponent;
  let fixture: ComponentFixture<ReviewCardListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewCardListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
