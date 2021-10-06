import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ExamRegistration, Student } from 'src/app/core';
import { HttpExamRegistrationService } from 'src/app/core/service/http-exam-registration.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-exam-registration-list',
  templateUrl: './exam-registration-list.component.html',
  styleUrls: ['./exam-registration-list.component.css']
})
export class ExamRegistrationListComponent implements OnInit {

  examRegistrations: ExamRegistration[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  constructor(private httpExamRegistration: HttpExamRegistrationService, private modalService: NgbModal,
     private toastService: ToastService) {}

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadExamRegistrations();
  }

  loadExamRegistrations() {
    this.httpExamRegistration
    .getByPage(this.currentPage - 1, this.pageSize)
    .subscribe((response) => {
      this.examRegistrations = response.content;
      this.totalItems = response.totalElements;
      this.pageSize = response.size;
      this.currentPage = response.number + 1;
  });
  }

  onSort(event: SortEvent) {
    console.log('sort event', event);

    this.headers.forEach((header) => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
    });
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadExamRegistrations();
  }

  onDeleteClick(examRegistration: ExamRegistration) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam registration for <strong>${examRegistration.student.firstName}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam registration';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExamRegistration(examRegistration)
    );
  }

  deleteSelectedExamRegistration(examRegistration: ExamRegistration) {
    this.httpExamRegistration.deleteExamRegistration(examRegistration).subscribe((response) => {
      this.toastService.show(
        'Exam registration deleted ',
        { header: 'Deleting exam registration', classname: 'bg-success text-light' }
      );
      this.loadExamRegistrations();
    });
  }

}
