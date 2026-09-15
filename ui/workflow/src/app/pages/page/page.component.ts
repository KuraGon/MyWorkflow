import {Component, ViewChild, signal, computed, inject, effect, WritableSignal} from '@angular/core';
import { MatSidenavModule, MatSidenav } from '@angular/material/sidenav';
import { RouterOutlet } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {map, shareReplay} from 'rxjs/operators';
import {NavbarComponent} from '../../components/navbar/navbar.component';
import {SidebarComponent} from '../../components/sidebar/sidebar.component';
import {AsyncPipe} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {FooterComponent} from '../../components/footer/footer.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatSidenavModule, RouterOutlet, NavbarComponent, SidebarComponent, AsyncPipe, FooterComponent],
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss'],
})
export class PageComponent {
  @ViewChild('snav') snav!: MatSidenav;

  // ✅ ouvert par défaut (large = 256px)
  readonly collapsed = signal(false);
  readonly sideWidth = computed(() => (this.collapsed() ? 70 : 240));
  stateCollapseBeforeOpened = signal(false);

  isHandset = inject(BreakpointObserver)
    .observe(Breakpoints.Handset)
    .pipe(map(r => r.matches), shareReplay(1));

  constructor() {}
}
