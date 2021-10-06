import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Title } from '../models/title.model';

@Injectable({
  providedIn: 'root'
})
export class HttpTitleService {

  controllerPrefix = 'title';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Title[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }
}
