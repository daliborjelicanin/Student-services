import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { SubjectDto } from 'src/app/core';
import { Subject } from 'rxjs';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastService } from 'src/app/core/service/toast.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css'],
})
export class SubjectListComponent implements OnInit {
  subjects: SubjectDto[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  destroy$: Subject<boolean> = new Subject();

  constructor(
    private httpSubject: HttpSubjectService,
    private modalService: NgbModal,
    private toastService: ToastService
  ) {}

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadSubjects();
  }

  loadSubjects() {
    this.httpSubject
      .getByPage(this.currentPage - 1, this.pageSize)
      .subscribe((response) => {
        this.subjects = response.content;
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
    this.loadSubjects();
  }

  onDeleteClick(subject: SubjectDto) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${subject.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting subject';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedSubject(subject)
    );
  }

  deleteSelectedSubject(subject: SubjectDto) {
    this.httpSubject.deleteSubject(subject).subscribe(
      (response) => {
        this.toastService.show('Subject deleted ', {
          header: 'Deleting subject',
          classname: 'bg-success text-light',
        });
        this.loadSubjects();
      },
      (error) => {
        this.toastService.show(
          'Subject is not deleted: ' +
            (typeof error.error === 'string' ? error.error : error.mesage),
          { header: 'Deleting subject', classname: 'bg-danger text-light' }
        );
      }
    );
  }
}
