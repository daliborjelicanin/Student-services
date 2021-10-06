import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';
import { Professor } from '../models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class HttpProfessorService {

  controllerPrefix = 'professor';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Professor[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getAllByEngagement(subjectId: number) {
    return this.httpClient.get<Professor[]>(`${environment.baseHttpURL}/${this.controllerPrefix}/engagement/${subjectId}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Professor[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<Professor>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  insertProfessor(professor: Professor) {
    return this.httpClient.post<Professor>(`${environment.baseHttpURL}/${this.controllerPrefix}`, professor)
  }

  updateProfessor(professor: Professor) {
    console.log(professor);
    return this.httpClient.put<Professor>(`${environment.baseHttpURL}/${this.controllerPrefix}`, professor)
  }

  deleteProfessor(professor: Professor) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${professor.id}`, {responseType: 'text'})
  }
}
