import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { StudentListComponent } from './pages/student-list/student-list.component';

const routes: Routes = [
  {path:'student-list', component: StudentListComponent},
  {path:'student-details/:id', component: StudentDetailsComponent},
  {path:'student-form/:id', component: StudentFormComponent, data: {edit: true}},
  {path:'student-form', component: StudentFormComponent, data: {edit: false}},
  {path:'', redirectTo:'student-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
