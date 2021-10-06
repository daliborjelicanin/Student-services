import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ExamPeriod } from 'src/app/core/models/exam-period.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-exam-period-list',
  templateUrl: './exam-period-list.component.html',
  styleUrls: ['./exam-period-list.component.css']
})
export class ExamPeriodListComponent implements OnInit {

  examPeriods: ExamPeriod[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  constructor(private httpExamPeriod: HttpExamPeriodService, private modalService: NgbModal,
    private toastService: ToastService) {}

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadExamPeriods();
  }

  loadExamPeriods() {
    this.httpExamPeriod
    .getByPage(this.currentPage - 1, this.pageSize)
    .subscribe((response) => {
      this.examPeriods = response.content;
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
    this.loadExamPeriods();
  }

  onDeleteClick(examPeriod: ExamPeriod) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam period <strong>${examPeriod.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam period';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExamPeriod(examPeriod)
    );
  }

  deleteSelectedExamPeriod(examPeriod: ExamPeriod) {
    this.httpExamPeriod.deleteExamPeriod(examPeriod).subscribe((response) => {
      this.toastService.show(
        'Exam period deleted ',
        { header: 'Deleting exam period', classname: 'bg-success text-light' }
      );
      this.loadExamPeriods();
    },
    (error) => {
      this.toastService.show(
        'Exam period is not deleted: ' +
          (typeof error.error === 'string' ? error.error : error.mesage),
        { header: 'Deleting exam period', classname: 'bg-danger text-light' }
      );
    }
    );
  }

}
