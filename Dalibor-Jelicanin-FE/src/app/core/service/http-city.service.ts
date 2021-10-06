import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { City } from '../models';

@Injectable({
  providedIn: 'root'
})
export class HttpCityService {

  controllerPrefix = 'city';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<City[]>(`${environment.baseHttpURL}/${this.controllerPrefix}`)
  }
}
