import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { City, Student } from 'src/app/core';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit {

  studentForm: FormGroup;
  edit: false;
  cities: City[];

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
    private httpStudent: HttpStudentService, private toastService: ToastService, private httpCity: HttpCityService) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadCities();
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadStudent(id);
    } else {
      this.buildForm();
    }
  }

  loadStudent(id: number) {
    this.httpStudent.getById(id)
    .subscribe( student => {
      console.log('load', student);
      this.buildForm(student);
    });
  }

  buildForm(student?: Student) {
    this.studentForm = this.fb.group({
      indexNumber: [student? student.indexNumber: null, [Validators.required, Validators.minLength(4), Validators.maxLength(4)]],
      indexYear: [student? student.indexYear: '', [Validators.required, Validators.min(2000), Validators.max(2100)]],
      firstName: [student? student.firstName: null, [Validators.required, Validators.minLength(3)]],
      lastName: [student? student.lastName: null, [Validators.required, Validators.minLength(3)]],
      email: [student? student.email: null, [Validators.email]],
      address: [student? student.address: null, [Validators.minLength(3)]],
      currentYearOfStudy: [student? student.currentYearOfStudy: null, [Validators.required]],
      city: [student && student.city &&  this.cities?  this.cities.find(city => city.postalCode === student.city.postalCode): null, [Validators.required]]
    });
  }

  saveStudent() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.studentForm.value.id = id;
      return this.httpStudent.updateStudent(this.studentForm.value);
    } else {
      this.studentForm.value.id = 0;
      return this.httpStudent.insertStudent(this.studentForm.value)
    }
  }

  onSubmit() {
    this.saveStudent()
    .subscribe(
      subject => {
        this.toastService.show('Student saved!', {header:'Saving student', classname: 'bg-success text-light'});
        this.router.navigate(['/student/student-list'])
      },
      error => {
        this.toastService.show('Student is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving student', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.studentForm.get(componentName).dirty || this.studentForm.get(componentName).touched) &&
             this.studentForm.get(componentName).hasError(errorCode);
  }

  loadCities() {
    this.httpCity.getAll().subscribe(
      cities => {
        this.cities = cities;
      }
    );
  }
}
