import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SubjectDto } from '../models';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {

  controllerPrefix = 'subject';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<SubjectDto[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<SubjectDto[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<SubjectDto>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  insertSubject(subject: SubjectDto) {
    return this.httpClient.post<SubjectDto>(`${environment.baseHttpURL}/${this.controllerPrefix}`, subject)
  }

  updateSubject(subject: SubjectDto) {
    console.log(subject);
    return this.httpClient.put<SubjectDto>(`${environment.baseHttpURL}/${this.controllerPrefix}`, subject)
  }

  deleteSubject(subject: SubjectDto) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${subject.id}`, {responseType: 'text'})
  }

}
