import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatChipsModule } from '@angular/material/chips';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { PhoneLogsService } from '../../api/api/phoneLogs.service';
import { PhoneLogImportResultDTO } from '../../api/model/phoneLogImportResult';
import { PhoneLogImportHistoryDTO } from '../../api/model/phoneLogImportHistory';

@Component({
  selector: 'app-update-history',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    MatChipsModule,
    MatTableModule,
    MatSnackBarModule,
  ],
  templateUrl: './update-history.component.html',
  styleUrls: ['./update-history.component.scss'],
})
export class UpdateHistoryComponent implements OnInit {

  selectedFiles: File[] = [];
  isUploading = false;

  history: PhoneLogImportHistoryDTO[] = [];

  displayedColumns: string[] = ['date', 'fileName', 'size', 'status', 'message'];

  constructor(
    private phoneLogsService: PhoneLogsService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.refreshHistory();
  }

  onFilesSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files) {
      return;
    }
    this.selectedFiles = Array.from(input.files);
  }

  clearSelection(): void {
    this.selectedFiles = [];
  }

  upload(): void {
    if (this.selectedFiles.length === 0 || this.isUploading) {
      return;
    }

    this.isUploading = true;

    // ⚠️ typescript-angular génère en général une signature:
    // importPhoneLogs(params?: { files?: Blob[]; }): Observable<PhoneLogImportResultDTO>
    this.phoneLogsService.importPhoneLogs(this.selectedFiles).subscribe({
      next: (result: PhoneLogImportResultDTO) => {
        this.isUploading = false;

        const imported = result.importedRows ?? 0;
        const total = result.totalRows ?? 0;

        this.snackBar.open(
          `Import terminé : ${imported}/${total} lignes`,
          'OK',
          { duration: 4000 }
        );

        this.clearSelection();
        this.refreshHistory();
      },
      error: () => {
        this.isUploading = false;
        this.snackBar.open('Erreur lors de l’import', 'Fermer', {
          duration: 5000,
        });
      },
    });
  }

  private refreshHistory(): void {
    this.phoneLogsService.getPhoneLogImports(10).subscribe({
      next: (items: PhoneLogImportHistoryDTO[]) => {
        this.history = items ?? [];
      },
      error: () => {
        this.history = [];
      },
    });
  }

  isSuccess(row: PhoneLogImportHistoryDTO): boolean {
    return row.status === 'SUCCESS';
  }

  isFailed(row: PhoneLogImportHistoryDTO): boolean {
    return row.status === 'FAILED';
  }
}
