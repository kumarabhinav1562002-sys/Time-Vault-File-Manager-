import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FileManagerService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  upload(file: File, expireMinutes?: number): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    if (expireMinutes) formData.append('expireMinutes', expireMinutes.toString());
    return this.http.post(`${this.baseUrl}/upload`, formData, { observe: 'response' });
  }

  getAllFiles(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/all`);
  }

  downloadFile(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/download/${id}`, { responseType: 'blob' });
  }

  deleteFile(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getMetadata(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/metadata`);
  }
}
