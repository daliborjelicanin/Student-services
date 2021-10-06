import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Exam, ExamPeriod, Professor, SubjectDto } from 'src/app/core';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-form',
  templateUrl: './exam-form.component.html',
  styleUrls: ['./exam-form.component.css']
})
export class ExamFormComponent implements OnInit {

  examForm: FormGroup;
  edit: false;
  examPeriods: ExamPeriod[];
  professors: Professor[];
  subjects: SubjectDto[];

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private httpExam: HttpExamService, private httpExamPeriod: HttpExamPeriodService,
              private httpProfessor: HttpProfessorService, private httpSubject: HttpSubjectService,
              private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadExamPeriods();
    this.loadProfessors();
    this.loadSubjects();
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExam(id);
    } else {
      this.buildForm();
    }
  }

  loadExam(id: number) {
    this.httpExam.getById(id)
    .subscribe( exam => {
      console.log('load', exam);
      this.buildForm(exam);
    });
  }

  buildForm(exam?: Exam) {
    this.examForm = this.fb.group({
      date: [exam? exam.date: null, [Validators.required]],
      examPeriod: [exam? exam.examPeriod: null, [Validators.required]],
      professor: [exam? exam.professor: null, [Validators.required]],
      subject: [exam? exam.subject: null, [Validators.required]]
    });
  }

  saveExam() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.examForm.value.id = id;
      return this.httpExam.updateExam(this.examForm.value);
    } else {
      this.examForm.value.id = 0;
      return this.httpExam.insertExam(this.examForm.value)
    }
  }

  onSubmit() {
    this.saveExam()
    .subscribe(
      examPeriod => {
        this.toastService.show('Exam saved!', {header:'Saving exam', classname: 'bg-success text-light'});
        this.router.navigate(['/exam/exam-list'])
      },
      error => {
        this.toastService.show('Exam is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving exam', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.examForm.get(componentName).dirty || this.examForm.get(componentName).touched) &&
             this.examForm.get(componentName).hasError(errorCode);
  }

  loadExamPeriods() {
    this.httpExamPeriod.getAll().subscribe(
      examPeriods => {
        this.examPeriods = examPeriods;
      }
    );
  }

  loadProfessors() {
    if(!this.examForm.get("subject").value) return;
    const subjectId = this.examForm.get("subject").value.id;
    if (!subjectId) return;
    this.httpProfessor.getAllByEngagement(subjectId).subscribe(
      professors => {
        this.professors = professors;
      }
    );
  }

  loadSubjects() {
    this.httpSubject.getAll().subscribe(
      subjects => {
        this.subjects = subjects;
      }
    );
  }

}
