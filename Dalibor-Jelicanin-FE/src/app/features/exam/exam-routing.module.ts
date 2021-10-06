import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamDetailsComponent } from './pages/exam-details/exam-details.component';
import { ExamFormComponent } from './pages/exam-form/exam-form.component';
import { ExamListComponent } from './pages/exam-list/exam-list.component';

const routes: Routes = [
  {path:'exam-list', component: ExamListComponent},
  {path:'exam-details/:id', component: ExamDetailsComponent},
  {path:'exam-form/:id', component: ExamFormComponent, data: {edit: true}},
  {path:'exam-form', component: ExamFormComponent, data: {edit: false}},
  {path:'', redirectTo:'exam-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamRoutingModule { }
