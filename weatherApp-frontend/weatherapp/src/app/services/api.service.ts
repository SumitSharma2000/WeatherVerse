import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = environment.apiBaseUrl; // API Gateway URL

  constructor(private readonly http: HttpClient) {}

  get<T>(
    endpoint: string, 
    params?: Record<string, string | number | boolean>
  ): Observable<T> {
    const url = `${this.baseUrl}${endpoint}`;
    
    let httpParams = new HttpParams();
    if (params) {
      Object.entries(params).forEach(([key, value]) => {
        httpParams = httpParams.set(key, value.toString());
      });
    }

    return this.http.get<T>(url, { params: httpParams });
  }
}
