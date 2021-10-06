import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { City, Professor, SubjectDto, Title } from 'src/app/core';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { HttpTitleService } from 'src/app/core/service/http-title.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-professor-add',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements OnInit {

  professorForm: FormGroup;
  edit: false;
  cities: City[];
  titles: Title[];
  subjects: SubjectDto[];
  selectedSubject = new FormControl();

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
    private httpProfessor: HttpProfessorService, private toastService: ToastService, private httpCity: HttpCityService,
    private httpTitle: HttpTitleService, private httpSubject: HttpSubjectService) { }

  ngOnInit(): void {
    this.prepareData();
    this.loadCities();
    this.loadTitles();
    this.loadSubjects();
  }

  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadProfessor(id);
    } else {
      this.buildForm();
    }
  }

  loadProfessor(id: number) {
    this.httpProfessor.getById(id)
    .subscribe( professor => {
      console.log('load', professor);
      this.buildForm(professor);
    });
  }

  buildForm(professor?: Professor) {
    this.professorForm = this.fb.group({
      firstName: [professor? professor.firstName: null, [Validators.required, Validators.minLength(3)]],
      lastName: [professor? professor.lastName: null, [Validators.required, Validators.minLength(3)]],
      email: [professor? professor.email: null, [Validators.email]],
      address: [professor? professor.address: null, [Validators.minLength(3)]],
      phone: [professor? professor.phone: null, [Validators.minLength(9)]],
      reelectionDate: [professor? professor.reelectionDate: null, [Validators.required]],
      title: [professor && professor.title && this.titles? this.titles.find(title => title.id === professor.title.id) : null, [Validators.required]],
      city: [professor && professor.city && this.cities?  this.cities.find(city => city.postalCode === professor.city.postalCode): null, [Validators.required]],
      subjects: this.buildSubjects(professor)
    });
  }

  saveProfessor() {
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.professorForm.value.id = id;
      return this.httpProfessor.updateProfessor(this.professorForm.value);
    } else {
      this.professorForm.value.id = 0;
      return this.httpProfessor.insertProfessor(this.professorForm.value)
    }
  }

  onSubmit() {
    this.saveProfessor()
    .subscribe(
      professor => {
        this.toastService.show('Professor saved!', {header:'Saving professor', classname: 'bg-success text-light'});
        this.router.navigate(['/professor/professor-list'])
      },
      error => {
        this.toastService.show('Professor is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving professor', classname: 'bg-danger text-light'});
      }
    );
  }

  hasErrors(componentName: string, errorCode: string) {
    return  (this.professorForm.get(componentName).dirty || this.professorForm.get(componentName).touched) &&
             this.professorForm.get(componentName).hasError(errorCode);
  }

  loadCities() {
    this.httpCity.getAll().subscribe(
      cities => {
        this.cities = cities;
      }
    );
  }

  loadTitles() {
    this.httpTitle.getAll().subscribe(
      titles => {
        this.titles = titles;
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

  addSelectedSubject() {
    if (this.professorForm.get('subjects').value.findIndex(subject => subject.id===this.selectedSubject.value.id) < 0) {
      (this.professorForm.get('subjects') as FormArray).push(this.buildSubjectGroup(this.selectedSubject.value))
    }
  }

  buildSubjectGroup(subject: SubjectDto) {
    console.log('subject',subject);
      return this.fb.group(
        {
          id: [subject.id],
          name: [subject.name],
          description: [subject.description],
          noOfESP: [subject.noOfESP],
          yearOfStudy: [subject.yearOfStudy],
          semester: [subject.semester],
        }
      );
  }

  removeSubjectAtIndex(i:number) {
    (this.professorForm.get('subjects') as FormArray).removeAt(i);
  }

  buildSubjects(professor: Professor) {
    const subjectsFormArray = this.fb.array([]);
    if (professor && professor.subjects) {
      professor.subjects.forEach(subject => subjectsFormArray.push(this.buildSubjectGroup(subject)));
    }


    return subjectsFormArray;
  }

}
