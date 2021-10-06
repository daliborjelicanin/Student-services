import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../models';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpStudentService {

  controllerPrefix = 'student';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Student[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Student[]>>(`${environment.baseHttpURL}/${this.controllerPrefix}/page?page=${page}&size=${size}`)
  }

  getById(id: number) {
    return this.httpClient.get<Student>(`${environment.baseHttpURL}/${this.controllerPrefix}/${id}`)
  }

  insertStudent(student: Student) {
    console.log(student);
    return this.httpClient.post<Student>(`${environment.baseHttpURL}/${this.controllerPrefix}`, student)
  }

  updateStudent(student: Student) {
    console.log(student);
    return this.httpClient.put<Student>(`${environment.baseHttpURL}/${this.controllerPrefix}`, student)
  }

  deleteStudent(student: Student) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controllerPrefix}/${student.id}`, {responseType: 'text'})
  }

}
