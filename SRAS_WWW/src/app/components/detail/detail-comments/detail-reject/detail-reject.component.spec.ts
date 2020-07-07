import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailRejectComponent } from './detail-reject.component';

describe('DetailRejectComponent', () => {
  let component: DetailRejectComponent;
  let fixture: ComponentFixture<DetailRejectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailRejectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailRejectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
