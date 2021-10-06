import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamPeriodFormComponent } from './exam-period-form.component';

describe('ExamPeriodFormComponent', () => {
  let component: ExamPeriodFormComponent;
  let fixture: ComponentFixture<ExamPeriodFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamPeriodFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamPeriodFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
