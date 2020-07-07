import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCommentInputComponent } from './detail-comment-input.component';

describe('DetailCommentInputComponent', () => {
  let component: DetailCommentInputComponent;
  let fixture: ComponentFixture<DetailCommentInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailCommentInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCommentInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
