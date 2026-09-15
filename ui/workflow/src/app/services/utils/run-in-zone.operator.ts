import { ChangeDetectorRef, NgZone } from '@angular/core';
import { MonoTypeOperatorFunction, Observable } from 'rxjs';

/**
 * Force les émissions RxJS (next/error/complete) à repasser dans la zone Angular.
 * Optionnellement, marque le composant pour check (utile si OnPush).
 */
export function runInZone<T>(zone: NgZone, cdr?: ChangeDetectorRef): MonoTypeOperatorFunction<T> {
  return (source) =>
    new Observable<T>((observer) =>
      source.subscribe({
        next: (value) =>
          zone.run(() => {
            observer.next(value);
            cdr?.markForCheck();
          }),
        error: (err) =>
          zone.run(() => {
            observer.error(err);
            cdr?.markForCheck();
          }),
        complete: () =>
          zone.run(() => {
            observer.complete();
            cdr?.markForCheck();
          }),
      })
    );
}
