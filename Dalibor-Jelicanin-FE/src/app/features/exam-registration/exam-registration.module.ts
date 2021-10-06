import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRegistrationRoutingModule } from './exam-registration-routing.module';
import { SharedModule } from 'src/app/shared';
import { ExamRegistrationListComponent } from './pages/exam-registration-list/exam-registration-list.component';
import { ExamRegistrationDetailsComponent } from './pages/exam-registration-details/exam-registration-details.component';
import { ExamRegistrationFormComponent } from './pages/exam-registration-form/exam-registration-form.component';


@NgModule({
  declarations: [
    ExamRegistrationListComponent,
    ExamRegistrationDetailsComponent,
    ExamRegistrationFormComponent
  ],
  imports: [
    CommonModule,
    ExamRegistrationRoutingModule,
    SharedModule
  ]
})
export class ExamRegistrationModule { }
