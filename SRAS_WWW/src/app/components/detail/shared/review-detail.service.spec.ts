import { TestBed, inject } from '@angular/core/testing';

import { ReviewDetailService } from './review-detail.service';

describe('ReviewDetailService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReviewDetailService]
    });
  });

  it('should ...', inject([ReviewDetailService], (service: ReviewDetailService) => {
    expect(service).toBeTruthy();
  }));
});
