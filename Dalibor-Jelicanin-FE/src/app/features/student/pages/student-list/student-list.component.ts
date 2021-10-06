import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Student } from 'src/app/core';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  constructor(private httpStudent: HttpStudentService,  private modalService: NgbModal, private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents() {
    this.httpStudent
    .getByPage(this.currentPage - 1, this.pageSize)
    .subscribe((response) => {
      this.students = response.content;
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
    this.loadStudents();
  }

  onDeleteClick(student: Student) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete student <strong>${student.firstName} ${student.lastName}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting student';
    modalRef.result.then(
      // NAPOMENA: Ovde ce samo ako je zadovoljen prvi uslov izvrsiti ovo drugo.
      (result) => result === 'Ok' && this.deleteSelectedStudent(student)
    );
  }

  deleteSelectedStudent(student: Student) {
    this.httpStudent.deleteStudent(student).subscribe((response) => {
      this.toastService.show(
        'Student deleted ',
        { header: 'Deleting student', classname: 'bg-success text-light' }
      );
      this.loadStudents();
    },
    (error) => {
      this.toastService.show(
        'Student is not deleted: ' +
          (typeof error.error === 'string' ? error.error : error.mesage),
        { header: 'Deleting student', classname: 'bg-danger text-light' }
      );
    }
    );
  }
}
