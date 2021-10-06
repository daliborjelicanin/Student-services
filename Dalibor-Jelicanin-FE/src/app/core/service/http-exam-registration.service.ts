import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ExamRegistration } from '../models';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExamRegistrationService {

  controllerPrefix = 'exam-registration';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<ExamRegistration[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<ExamRegistration[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<ExamRegistration>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  insertExamRegistration(examRegistration: ExamRegistration) {
    return this.httpClient.post<ExamRegistration>(`${environment.baseHttpURL}/${this.controllerPrefix}`, examRegistration)
  }

  updateExamRegistration(examRegistration: ExamRegistration) {
    console.log(examRegistration);
    return this.httpClient.put<ExamRegistration>(`${environment.baseHttpURL}/${this.controllerPrefix}`, examRegistration)
  }

  deleteExamRegistration(examRegistration: ExamRegistration) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${examRegistration.id}`, {responseType: 'text'})
  }
}
