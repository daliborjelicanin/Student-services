import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamRegistrationDetailsComponent } from './pages/exam-registration-details/exam-registration-details.component';
import { ExamRegistrationFormComponent } from './pages/exam-registration-form/exam-registration-form.component';
import { ExamRegistrationListComponent } from './pages/exam-registration-list/exam-registration-list.component';

const routes: Routes = [
  {path:'exam-registration-list', component: ExamRegistrationListComponent},
  {path:'exam-registration-details/:id', component: ExamRegistrationDetailsComponent},
  {path:'exam-registration-form/:id', component: ExamRegistrationFormComponent, data: {edit: true}},
  {path:'exam-registration-form', component: ExamRegistrationFormComponent, data: {edit: false}},
  {path:'', redirectTo:'exam-registration-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamRegistrationRoutingModule { }
