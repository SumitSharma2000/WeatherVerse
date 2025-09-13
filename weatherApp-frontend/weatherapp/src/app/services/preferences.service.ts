import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class PreferencesService {
  unitCelsius = signal<boolean>(true);

  constructor(private readonly http: HttpClient) {}

  load(): void {
    this.http.get<{ unit: 'C' | 'F' }>(`/api/preferences/unit`).subscribe({
      next: (res) => this.unitCelsius.set(res.unit === 'C'),
      error: () => {},
    });
  }

  save(): void {
    const unit = this.unitCelsius() ? 'C' : 'F';
    this.http
      .get(`/api/preferences/set`, { params: new HttpParams({ fromObject: { unit } }) })
      .subscribe({ complete: () => {} });
  }
}
