import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamPeriod } from 'src/app/core';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-period-form',
  templateUrl: './exam-period-form.component.html',
  styleUrls: ['./exam-period-form.component.css']
})
export class ExamPeriodFormComponent implements OnInit {

  examPeriodForm: FormGroup;
  edit: false;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private httpExamPeriod: HttpExamPeriodService, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExamPeriod(id);
    } else {
      this.buildForm();
    }
  }

  loadExamPeriod(id: number) {
    this.httpExamPeriod.getById(id)
    .subscribe( examPeriod => {
      console.log('load', examPeriod);
      this.buildForm(examPeriod);
    });
  }

  buildForm(examPeriod?: ExamPeriod) {
    this.examPeriodForm = this.fb.group({
      name: [examPeriod? examPeriod.name: null, [Validators.required, Validators.minLength(3)]],
      startDate: [examPeriod? examPeriod.startDate: null, [Validators.required]],
      completionDate: [examPeriod? examPeriod.completionDate: null, [Validators.required]],
      active: [examPeriod? examPeriod.active: false]
    });
  }

  saveExamPeriod() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.examPeriodForm.value.id = id;
      return this.httpExamPeriod.updateExamPeriod(this.examPeriodForm.value);
    } else {
      this.examPeriodForm.value.id = 0;
      return this.httpExamPeriod.insertExamPeriod(this.examPeriodForm.value)
    }
  }

  onSubmit() {
    this.saveExamPeriod()
    .subscribe(
      examPeriod => {
        this.toastService.show('Exam period saved!', {header:'Saving exam period', classname: 'bg-success text-light'});
        this.router.navigate(['/exam-period/exam-period-list'])
      },
      error => {
        this.toastService.show('Exam period is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving exam period', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.examPeriodForm.get(componentName).dirty || this.examPeriodForm.get(componentName).touched) &&
             this.examPeriodForm.get(componentName).hasError(errorCode);
  }

}
