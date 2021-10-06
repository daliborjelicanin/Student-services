import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';
import { SharedModule } from 'src/app/shared';
import { SubjectRoutingModule } from './subject-routing.module';
import { SubjectDetailsComponent } from './pages/subject-details/subject-details.component';
import { SubjectAddComponent } from './pages/subject-form/subject-form.component';

@NgModule({
  declarations: [
    SubjectListComponent,
    SubjectDetailsComponent,
    SubjectAddComponent
  ],
  imports: [
    CommonModule,
    SubjectRoutingModule,
    SharedModule
  ]
})
export class SubjectModule { }
