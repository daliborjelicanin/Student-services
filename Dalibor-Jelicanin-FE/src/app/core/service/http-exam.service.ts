import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.model';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExamService {

  controllerPrefix = 'exam';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getAllForActiveExamPeriod() {
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controllerPrefix}/active`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Exam[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<Exam>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  insertExam(exam: Exam) {
    return this.httpClient.post<Exam>(`${environment.baseHttpURL}/${this.controllerPrefix}`, exam)
  }

  updateExam(exam: Exam) {
    console.log(exam);
    return this.httpClient.put<Exam>(`${environment.baseHttpURL}/${this.controllerPrefix}`, exam)
  }

  deleteExam(exam: Exam) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${exam.id}`, {responseType: 'text'})
  }
}
