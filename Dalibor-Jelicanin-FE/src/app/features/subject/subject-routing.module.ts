import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectAddComponent } from './pages/subject-form/subject-form.component';
import { SubjectDetailsComponent } from './pages/subject-details/subject-details.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';


const routes: Routes = [
  {path:'subject-list', component: SubjectListComponent},
  {path:'subject-details/:id', component: SubjectDetailsComponent},
  {path:'subject-form/:id', component: SubjectAddComponent, data: {edit: true}},
  {path:'subject-form', component: SubjectAddComponent, data: {edit: false}},
  {path:'', redirectTo:'subject-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
