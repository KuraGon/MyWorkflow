import { Component, Input, Output, EventEmitter, signal } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgClass } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';

type Item   = { label: string; icon: string; link: string };
type Section = { title: string; items: Item[] };

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, MatIconModule, MatTooltipModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  @Input() embedded = false;

  /** Etat rail (true = compact 72px / false = large 256px). */
  @Input() collapsed = false;
  @Output() collapsedChange = new EventEmitter<boolean>();

  constructor() {
  }

  /** Bascule compact/large. */
  toggle() {
    this.collapsed = !this.collapsed;
    this.collapsedChange.emit(this.collapsed);
  }

  /** Données de menu (maquette). */
  readonly sections: Section[] = [
    {
      title: 'Téléphonie',
      items: [
        {
          label: 'Indicateurs Interne',
          icon: 'insights',
          link: '/phone/history'
        },
        {
          label: 'Indicateurs Client',
          icon: 'contacts',
          link: '/phone-user/'
        }
      ]
    },
    {
      title: 'Paramétrage',
      items: [
        {
          label: 'Mise à jour des suivis',
          icon: 'sync',
          link: '/phone/settings/suivis'
        },
      ]
    }
  ];
}
