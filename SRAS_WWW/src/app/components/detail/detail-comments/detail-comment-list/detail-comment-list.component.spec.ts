import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCommentListComponent } from './detail-comment-list.component';

describe('DetailCommentListComponent', () => {
  let component: DetailCommentListComponent;
  let fixture: ComponentFixture<DetailCommentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailCommentListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCommentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
