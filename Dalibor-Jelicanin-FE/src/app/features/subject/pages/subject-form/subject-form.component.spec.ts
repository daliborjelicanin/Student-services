import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectAddComponent } from './subject-form.component';

describe('SubjectFormComponent', () => {
  let component: SubjectAddComponent;
  let fixture: ComponentFixture<SubjectAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubjectAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
