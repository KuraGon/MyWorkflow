import { Component } from '@angular/core';
import { DatePipe, CurrencyPipe, NgClass, DecimalPipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';

type Order = {
  date: string; // ISO
  type: string;
  qty: number;
  poids?: string;
  facon?: string;
};

type MetalRow = { code: 'Au'|'Ag'|'Pt'|'Pd'; name: string; est: string; solde: string };

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './home.component.html',
})
export class HomeComponent {

  // Onglets (juste visuel)
  tabs = [
    { icon: 'assignment', label: 'Mes Commandes', active: true },
    { icon: 'inventory_2', label: 'Mes remises de métaux sauvegardées', active: false },
  ];

  // En cours (maquette)
  inProgress: Order[] = [
    { date: '2024-10-14', type: 'PRODUIT', qty: 1, poids: '5.00 g' },
  ];

  // Terminées (maquette)
  done: Order[] = [
    { date: '2024-10-08', type: 'PRODUIT', qty: 1, poids: '2.00 g', facon: '12.00 €' },
    { date: '2024-10-02', type: 'PRODUIT', qty: 1, poids: '171.55 g', facon: '481.83 €' },
    { date: '2024-10-07', type: 'PRODUIT', qty: 1, poids: '2.00 g', facon: '12.00 €' },
  ];

  metals: MetalRow[] = [
    { code: 'Au', name: 'Or',        est: '99.00 g',  solde: '99.00 g'  },
    { code: 'Ag', name: 'Argent',    est: '10.00 g',  solde: '10.00 g'  },
    { code: 'Pt', name: 'Platine',   est: '0.00 g',   solde: '0.00 g'   },
    { code: 'Pd', name: 'Palladium', est: '0.00 g',   solde: '0.00 g'   },
  ];

  eurosBalance = 0.00;
}
