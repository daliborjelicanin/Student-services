import { TestBed } from '@angular/core/testing';

import { HttpExamRegistrationService } from './http-exam-registration.service';

describe('HttpExamRegistrationService', () => {
  let service: HttpExamRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpExamRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
