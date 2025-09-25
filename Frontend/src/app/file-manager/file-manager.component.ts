import { Component, OnInit, OnDestroy } from '@angular/core';
import { FileManagerService } from './file-manager.service';

@Component({
  selector: 'app-file-manager',
  templateUrl: './file-manager.component.html',
  styleUrls: ['./file-manager.component.scss']
})
export class FileManagerComponent implements OnInit, OnDestroy {
  files: any[] = [];
  selectedFile: File | null = null;
  expireMinutes: number | null = null;
  message = '';
  intervalId: any;

  constructor(private fileService: FileManagerService) {}

  ngOnInit() {
    this.loadFiles();
    this.intervalId = setInterval(() => this.loadFiles(), 10000); // auto-refresh every 10s
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  upload() {
    if (!this.selectedFile) return;
    this.fileService.upload(this.selectedFile, this.expireMinutes).subscribe({
      next: res => {
        this.message = 'Upload successful!';
        this.loadFiles();
      },
      error: err => this.message = 'Upload failed: ' + (err.error?.message || err.statusText)
    });
  }

  loadFiles() {
    this.fileService.getAllFiles().subscribe(files => this.files = files);
  }

  download(id: number, name: string) {
    this.fileService.downloadFile(id).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = name;
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  delete(id: number) {
    this.fileService.deleteFile(id).subscribe(() => this.loadFiles());
  }

  isExpired(file: any): boolean {
    return file.expireTime && new Date(file.expireTime) < new Date();
  }
}
