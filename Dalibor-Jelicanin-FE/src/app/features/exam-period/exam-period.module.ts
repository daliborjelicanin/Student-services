import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamPeriodRoutingModule } from './exam-period-routing.module';
import { SharedModule } from 'src/app/shared';
import { ExamPeriodListComponent } from './pages/exam-period-list/exam-period-list.component';
import { ExamPeriodDetailsComponent } from './pages/exam-period-details/exam-period-details.component';
import { ExamPeriodFormComponent } from './pages/exam-period-form/exam-period-form.component';


@NgModule({
  declarations: [
    ExamPeriodListComponent,
    ExamPeriodDetailsComponent,
    ExamPeriodFormComponent
  ],
  imports: [
    CommonModule,
    ExamPeriodRoutingModule,
    SharedModule
  ]
})
export class ExamPeriodModule { }
