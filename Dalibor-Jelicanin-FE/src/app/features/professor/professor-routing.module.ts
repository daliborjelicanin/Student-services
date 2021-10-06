import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorFormComponent } from './pages/professor-form/professor-form.component';
import { ProfessorDetailsComponent } from './pages/professor-details/professor-details.component';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';

const routes: Routes = [
  {path:'professor-list', component: ProfessorListComponent},
  {path:'professor-details/:id', component: ProfessorDetailsComponent},
  {path:'professor-form/:id', component: ProfessorFormComponent, data: {edit: true}},
  {path:'professor-form', component: ProfessorFormComponent, data: {edit: false}},
  {path:'', redirectTo:'professor-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessorRoutingModule { }
