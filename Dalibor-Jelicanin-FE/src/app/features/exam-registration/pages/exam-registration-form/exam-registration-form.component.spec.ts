import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamRegistrationFormComponent } from './exam-registration-form.component';

describe('ExamRegistrationFormComponent', () => {
  let component: ExamRegistrationFormComponent;
  let fixture: ComponentFixture<ExamRegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamRegistrationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamRegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
