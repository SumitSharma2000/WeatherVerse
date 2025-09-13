import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class PreferencesService {
  unitCelsius = signal<boolean>(true);

  constructor(private readonly http: HttpClient) {}

  load(): void {
    this.http.get<{ unit: 'C' | 'F' }>(`/preferences/unit`).subscribe({
      next: (res: { unit: 'C' | 'F' }) => this.unitCelsius.set(res.unit === 'C'),
      error: () => {}
    });
  }

  save(): void {
    const unit = this.unitCelsius() ? 'C' : 'F';
    // fire and forget
    this.http.get(`/preferences/set`, { params: new HttpParams({ fromObject: { unit } }) }).subscribe({ complete: () => {} });
  }
}
