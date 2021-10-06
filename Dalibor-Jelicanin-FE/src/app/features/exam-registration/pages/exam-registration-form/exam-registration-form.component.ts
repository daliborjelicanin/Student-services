import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Exam, ExamPeriod, ExamRegistration, Student } from 'src/app/core';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { HttpExamRegistrationService } from 'src/app/core/service/http-exam-registration.service';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-registration-form',
  templateUrl: './exam-registration-form.component.html',
  styleUrls: ['./exam-registration-form.component.css']
})
export class ExamRegistrationFormComponent implements OnInit {

  examRegistrationForm: FormGroup;
  edit: false;
  examPeriod: ExamPeriod;
  exams: Exam[];
  students: Student[];
  selectedExam = new FormControl();

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private httpExam: HttpExamService, private httpExamPeriod: HttpExamPeriodService,
              private httpExamRegistration: HttpExamRegistrationService, private httpStudent: HttpStudentService,
              private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadExamPeriod();
    this.loadExams();
    this.loadStudents();
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExamRegistration(id);
    } else {
      this.buildForm();
    }
  }

  loadExamRegistration(id: number) {
    this.httpExamRegistration.getById(id)
    .subscribe( examRegistration => {
      console.log('load', examRegistration);
      this.buildForm(examRegistration);
    });
  }

  buildForm(examRegistration?: ExamRegistration) {
    this.examRegistrationForm = this.fb.group({
      student: [examRegistration? examRegistration.student: null, [Validators.required]],
      exam: [examRegistration? examRegistration.exam: null, [Validators.required]],
      date: [examRegistration? examRegistration.date: null, [Validators.required]],
      exams: this.fb.array([])
    });
  }

  saveExamRegistration() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.examRegistrationForm.value.id = id;
      return this.httpExamRegistration.updateExamRegistration(this.examRegistrationForm.value);
    } else {
      this.examRegistrationForm.value.id = 0;
      return this.httpExamRegistration.insertExamRegistration(this.examRegistrationForm.value)
    }
  }

  onSubmit() {
    this.saveExamRegistration()
    .subscribe(
      examRegistration => {
        this.toastService.show('Exam registration saved!', {header:'Saving exam registration', classname: 'bg-success text-light'});
        this.router.navigate(['/exam-registration/exam-registration-list'])
      },
      error => {
        this.toastService.show('Exam registration is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving exam registration', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.examRegistrationForm.get(componentName).dirty || this.examRegistrationForm.get(componentName).touched) &&
             this.examRegistrationForm.get(componentName).hasError(errorCode);
  }

  loadExamPeriod() {
    this.httpExamPeriod.getActiveExamPeriod().subscribe(
      examPeriod => {
        this.examPeriod = examPeriod;
      }
    );
  }

  loadExams() {
    this.httpExam.getAllForActiveExamPeriod().subscribe(
      exams => {
        this.exams = exams;
      }
    );
  }

  loadStudents() {
    this.httpStudent.getAll().subscribe(
      students => {
        this.students = students;
      }
    );
  }

  addSelectedExam() {
    if (this.examRegistrationForm.get('exams').value.findIndex(subject => subject.id===this.selectedExam.value.id) < 0) {
      (this.examRegistrationForm.get('exams') as FormArray).push(this.buildExamGroup(this.selectedExam.value))
    }
  }

  buildExamGroup(exam: Exam) {
    console.log('exam',exam);
      return this.fb.group(
        {
          id: [exam.id],
          date: [exam.date],
          examPeriod: [exam.examPeriod],
          professor: [exam.professor],
          subject: [exam.subject],
        }
      );
  }

  removeExamAtIndex(i:number) {
    (this.examRegistrationForm.get('exams') as FormArray).removeAt(i);
  }

}
