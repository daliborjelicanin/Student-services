import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ExamPeriod } from '../models/exam-period.model';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExamPeriodService {

  controllerPrefix = 'exam-period';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<ExamPeriod[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<ExamPeriod[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<ExamPeriod>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  getActiveExamPeriod() {
    return this.httpClient.get<ExamPeriod>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  insertExamPeriod(examPeriod: ExamPeriod) {
    return this.httpClient.post<ExamPeriod>(`${environment.baseHttpURL}/${this.controllerPrefix}`, examPeriod)
  }

  updateExamPeriod(examPeriod: ExamPeriod) {
    console.log(examPeriod);
    return this.httpClient.put<ExamPeriod>(`${environment.baseHttpURL}/${this.controllerPrefix}`, examPeriod)
  }

  deleteExamPeriod(examPeriod: ExamPeriod) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${examPeriod.id}`, {responseType: 'text'})
  }
}
