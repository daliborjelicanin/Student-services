import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ObjectInfoPipe } from './pipes';
import { SortableHeaderDirective } from './directives/sortable-header.directive';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { GlobalToastComponent } from './components/global-toast/global-toast.component';



@NgModule({
  declarations: [
    HeaderComponent,
    ObjectInfoPipe,
    SortableHeaderDirective,
    ConfirmDialogComponent,
    GlobalToastComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    NgbModule,
    HttpClientModule
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    NgbModule,
    GlobalToastComponent,

    HeaderComponent,
    ObjectInfoPipe,
    SortableHeaderDirective
  ]
})
export class SharedModule { }
