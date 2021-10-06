import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Semester, SubjectDto } from 'src/app/core';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-subject-add',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.css']
})
export class SubjectAddComponent implements OnInit {

  subjectForm: FormGroup;
  edit: false;
  semester = Semester;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private httpSubject: HttpSubjectService, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }

  getEnumKeys() {
    //return Object.keys(Semester);
    return Object.keys(Semester).map( key=> this.semester[key] );
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadSubject(id);
    } else {
      this.buildForm();
    }
  }

  loadSubject(id: number) {
    this.httpSubject.getById(id)
    .subscribe( subject => {
      console.log('load', subject);
      this.buildForm(subject);
    });
  }

  buildForm(subject?: SubjectDto) {
    this.subjectForm = this.fb.group({
      name: [subject? subject.name: '', [Validators.required, Validators.minLength(3)]],
      description: [subject? subject.description: ''],
      noOfESP: [subject? subject.noOfESP: '', [Validators.required]],
      yearOfStudy: [subject? subject.yearOfStudy: '', [Validators.required]],
      semester: [subject? subject.semester: '', [Validators.required]]
    });
  }

  saveSubject() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.subjectForm.value.id = id;
      return this.httpSubject.updateSubject(this.subjectForm.value);
    } else {
      this.subjectForm.value.id = 0;
      return this.httpSubject.insertSubject(this.subjectForm.value)
    }
  }

  onSubmit() {
    this.saveSubject()
    .subscribe(
      subject => {
        this.toastService.show('Subject saved!', {header:'Saving subject', classname: 'bg-success text-light'});
        this.router.navigate(['/subject/subject-list'])
      },
      error => {
        this.toastService.show('Subject is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving subject', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.subjectForm.get(componentName).dirty || this.subjectForm.get(componentName).touched) &&
             this.subjectForm.get(componentName).hasError(errorCode);
  }

}
