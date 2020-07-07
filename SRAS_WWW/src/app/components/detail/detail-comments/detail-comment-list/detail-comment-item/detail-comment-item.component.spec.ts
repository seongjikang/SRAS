import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCommentItemComponent } from './detail-comment-item.component';

describe('DetailCommentItemComponent', () => {
  let component: DetailCommentItemComponent;
  let fixture: ComponentFixture<DetailCommentItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailCommentItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCommentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
