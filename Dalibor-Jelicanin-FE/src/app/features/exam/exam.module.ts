import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRoutingModule } from './exam-routing.module';
import { SharedModule } from 'src/app/shared';
import { ExamListComponent } from './pages/exam-list/exam-list.component';
import { ExamDetailsComponent } from './pages/exam-details/exam-details.component';
import { ExamFormComponent } from './pages/exam-form/exam-form.component';


@NgModule({
  declarations: [
    ExamListComponent,
    ExamDetailsComponent,
    ExamFormComponent
  ],
  imports: [
    CommonModule,
    ExamRoutingModule,
    SharedModule
  ]
})
export class ExamModule { }
