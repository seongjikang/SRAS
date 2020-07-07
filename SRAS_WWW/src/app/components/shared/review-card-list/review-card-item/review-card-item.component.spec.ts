import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewCardItemComponent } from './review-card-item.component';

describe('ReviewCardItemComponent', () => {
  let component: ReviewCardItemComponent;
  let fixture: ComponentFixture<ReviewCardItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewCardItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewCardItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
