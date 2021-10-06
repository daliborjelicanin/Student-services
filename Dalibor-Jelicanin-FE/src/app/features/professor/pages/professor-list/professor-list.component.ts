import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  professors: Professor[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;

  constructor(private httpProfessor: HttpProfessorService,  private modalService: NgbModal, private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadProfessors();
  }

  loadProfessors() {
    this.httpProfessor
    .getByPage(this.currentPage - 1, this.pageSize)
    .subscribe((response) => {
      this.professors = response.content;
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
    this.loadProfessors();
  }

  onDeleteClick(professor: Professor) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete professor <strong>${professor.firstName} ${professor.lastName}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting professor';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedProfessor(professor)
    );
  }

  deleteSelectedProfessor(professor: Professor) {
    this.httpProfessor.deleteProfessor(professor).subscribe((response) => {
      this.toastService.show(
        'Professor deleted ',
        { header: 'Deleting professor', classname: 'bg-success text-light' }
      );
      this.loadProfessors();
    },
    (error) => {
      this.toastService.show(
        'Professor is not deleted: ' +
          (typeof error.error === 'string' ? error.error : error.mesage),
        { header: 'Deleting professor', classname: 'bg-danger text-light' }
      );
    }
    );
  }

}
