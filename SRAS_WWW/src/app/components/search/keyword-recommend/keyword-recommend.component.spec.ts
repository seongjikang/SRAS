import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KeywordRecommendComponent } from './keyword-recommend.component';

describe('KeywordRecommendComponent', () => {
  let component: KeywordRecommendComponent;
  let fixture: ComponentFixture<KeywordRecommendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KeywordRecommendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KeywordRecommendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
