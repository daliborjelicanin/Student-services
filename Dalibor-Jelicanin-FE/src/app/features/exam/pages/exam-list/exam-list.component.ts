import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exam } from 'src/app/core';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})
export class ExamListComponent implements OnInit {

  exams: Exam[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  constructor(private httpExam: HttpExamService, private modalService: NgbModal, private toastService: ToastService) {}

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadExams();
  }

  loadExams() {
    // this.httpExam
    // .getAll().subscribe(
    //   response => {
    //     this.exams = response;
    //   }
    // )
    this.httpExam
    .getByPage(this.currentPage - 1, this.pageSize)
    .subscribe((response) => {
      this.exams = response.content;
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
    this.loadExams();
  }

  onDeleteClick(exam: Exam) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam <strong>${exam.subject.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExam(exam)
    );
  }

  deleteSelectedExam(exam: Exam) {
    this.httpExam.deleteExam(exam).subscribe((response) => {
      this.toastService.show(
        'Exam deleted ',
        { header: 'Deleting exam', classname: 'bg-success text-light' }
      );
      this.loadExams();
    });
  }

}
