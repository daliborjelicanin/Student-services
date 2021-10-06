import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamRegistrationListComponent } from './exam-registration-list.component';

describe('ExamRegistrationListComponent', () => {
  let component: ExamRegistrationListComponent;
  let fixture: ComponentFixture<ExamRegistrationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamRegistrationListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamRegistrationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
