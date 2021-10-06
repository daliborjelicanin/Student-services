import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamRegistrationDetailsComponent } from './exam-registration-details.component';

describe('ExamRegistrationDetailsComponent', () => {
  let component: ExamRegistrationDetailsComponent;
  let fixture: ComponentFixture<ExamRegistrationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamRegistrationDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamRegistrationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
