import { TestBed } from '@angular/core/testing';

import { HttpExamPeriodService } from './http-exam-period.service';

describe('HttpExamPeriodService', () => {
  let service: HttpExamPeriodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpExamPeriodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
