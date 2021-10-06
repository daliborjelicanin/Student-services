import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { SharedModule } from 'src/app/shared';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';


@NgModule({
  declarations: [
    StudentListComponent,
    StudentDetailsComponent,
    StudentFormComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    SharedModule
  ]
})
export class StudentModule { }
