import {ChangeDetectionStrategy, Component, OnInit, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {forkJoin, Observable, of} from 'rxjs';
import {finalize} from 'rxjs/operators';

// Imports Angular Material
import {MatStepperModule} from '@angular/material/stepper';
import {MatListModule} from '@angular/material/list';
import {MatRadioModule} from '@angular/material/radio';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {
  PricingAdminService,
  NatureDTO,
  TarifRuleDTO,
  PriceGridDTO,
  PriceGridDTORuleTypeEnum, PricingService
} from '../../api';

@Component({
  selector: 'app-pricing-settings',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatStepperModule,
    MatListModule,
    MatRadioModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressBarModule,
    MatSnackBarModule
  ],
  templateUrl: './pricing-settings.component.html',
  styleUrl: './pricing-settings.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PricingSettingsComponent implements OnInit {

  // --- Données ---
  public natures$!: Observable<NatureDTO[]>;
  public rules$!: Observable<TarifRuleDTO[]>;

  // --- État ---
  public selectedNature: NatureDTO | null = null;
  public selectedRuleType: 'FORFAIT' | 'PRESTATION' | null = null;
  public selectedRule: TarifRuleDTO | null = null;

  public isLoadingRules = false;
  public isLoadingGrids = false;
  public isSaving = false;

  public gridForm: FormGroup;
  public editingRowKey: string | null = null;

  public displayedRuleColumns: string[] = ['description', 'gold', 'silver', 'platinum', 'palladium'];

  constructor(private pricingService: PricingAdminService,
              private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.gridForm = this.fb.group({
      grids: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.natures$ = this.pricingService.getNatures();
  }

  // --- NAVIGATION ---
  onNatureSelected(nature: NatureDTO): void {
    this.selectedNature = nature;
    this.selectedRuleType = null;
    this.selectedRule = null;
    this.rules$ = of([]);
    this.getGridsArray().clear();
  }

  onTypeSelected(type: 'FORFAIT' | 'PRESTATION'): void {
    if (!this.selectedNature?.id) return;

    this.selectedRuleType = type;
    this.selectedRule = null;
    this.getGridsArray().clear();
    this.isLoadingRules = true;

    this.rules$ = this.pricingService.getRules(this.selectedNature.id, type)
      .pipe(finalize(() => this.isLoadingRules = false));
  }

  onRuleSelected(rule: TarifRuleDTO): void {
    this.selectedRule = rule;
    this.isLoadingGrids = true;
    this.getGridsArray().clear();

    const gridObservables: Observable<PriceGridDTO>[] = [];

    if (rule.ruleType === 'FORFAIT' && rule.codeTarifForfait) {
      gridObservables.push(this.pricingService.getPriceGrid('FORFAIT', rule.codeTarifForfait));

    } else if (rule.ruleType === 'PRESTATION') {
      if (rule.codeTarifFonte) gridObservables.push(this.pricingService.getPriceGrid('PRESTATION', rule.codeTarifFonte));
      if (rule.codeTarifAnalyse) gridObservables.push(this.pricingService.getPriceGrid('PRESTATION', rule.codeTarifAnalyse));
      if (rule.codeTarifAffinage) gridObservables.push(this.pricingService.getPriceGrid('PRESTATION', rule.codeTarifAffinage));
      if (rule.codeTarifPreparation) gridObservables.push(this.pricingService.getPriceGrid('PRESTATION', rule.codeTarifPreparation));
    }

    if (gridObservables.length > 0) {
      forkJoin(gridObservables)
        .pipe(finalize(() => this.isLoadingGrids = false))
        .subscribe((grids) => {
          this.buildForm(grids);
        });
    } else {
      this.isLoadingGrids = false;
    }
  }

  // --- FORMULAIRE ---

  buildForm(grids: PriceGridDTO[]): void {
    const gridFormGroups = grids.map(grid => {
      const priceBreakControls = (grid.priceBreaks || []).map(pb => {
        return this.fb.group({
          id: [pb.id],
          pdsMin: [pb.pdsMin],
          pdsMax: [pb.pdsMax],
          prixBrutKg: [pb.prixBrutKg],
          prixBrutMin: [pb.prixBrutMin]
        });
      });

      return this.fb.group({
        ruleCode: [grid.ruleCode],
        ruleType: [grid.ruleType],
        priceBreaks: this.fb.array(priceBreakControls)
      });
    });

    this.gridForm.setControl('grids', this.fb.array(gridFormGroups));
  }

  getGridsArray(): FormArray {
    return this.gridForm.get('grids') as FormArray;
  }

  getPriceBreaksArray(gridIndex: number): FormArray {
    return this.getGridsArray().at(gridIndex).get('priceBreaks') as FormArray;
  }

  // --- EDIT ---

  startEdit(gridIndex: number, rowIndex: number): void {
    this.editingRowKey = `${gridIndex}-${rowIndex}`;
  }

  stopEdit(): void {
    this.editingRowKey = null;
  }

  isEditing(gridIndex: number, rowIndex: number): boolean {
    return this.editingRowKey === `${gridIndex}-${rowIndex}`;
  }

  getGridTitle(code: string): string {
    if (!this.selectedRule) return code;
    if (this.selectedRule.ruleType === 'FORFAIT') return 'Grille Forfait';
    if (code === this.selectedRule.codeTarifAffinage) return 'Affinage';
    if (code === this.selectedRule.codeTarifAnalyse) return 'Analyse';
    if (code === this.selectedRule.codeTarifFonte) return 'Fonte / Homogénéisation';
    if (code === this.selectedRule.codeTarifPreparation) return 'Préparation';
    return code;
  }

  // --- SAUVEGARDE ---

  saveChanges(): void {
    if (this.gridForm.invalid) return;

    this.isSaving = true;
    const gridsValue = this.gridForm.value.grids;
    const updateObservables: Observable<PriceGridDTO>[] = [];

    gridsValue.forEach((gridVal: any) => {

      const ruleTypeEnum = this.selectedRuleType === 'FORFAIT'
        ? PriceGridDTORuleTypeEnum.FORFAIT
        : PriceGridDTORuleTypeEnum.PRESTATION;

      const dtoToSave: PriceGridDTO = {
        ruleCode: gridVal.ruleCode,
        ruleType: ruleTypeEnum,
        priceBreaks: gridVal.priceBreaks.map((pb: any) => ({
          id: pb.id,
          pdsMin: Number(pb.pdsMin),
          pdsMax: Number(pb.pdsMax),
          prixBrutKg: Number(pb.prixBrutKg),
          prixBrutMin: Number(pb.prixBrutMin)
        }))
      };

      updateObservables.push(
        this.pricingService.updatePriceGrid(
          this.selectedRuleType!,
          gridVal.ruleCode,
          dtoToSave
        )
      );
    });

    if (updateObservables.length === 0) {
      this.isSaving = false;
      return;
    }

    forkJoin(updateObservables)
      .pipe(finalize(() => this.isSaving = false))
      .subscribe({
        next: () => {
          this.snackBar.open('Modifications enregistrées avec succès', 'OK', {duration: 3000});
          this.editingRowKey = null;
        },
        error: (err) => {
          console.error('Erreur sauvegarde', err);
          this.snackBar.open('Erreur lors de la sauvegarde', 'Fermer', {
            duration: 5000,
            panelClass: ['bg-red-500', 'text-white']
          });
        }
      });
  }

  getNatureIcon(libelle: string | undefined): string {
    if (!libelle) return 'category'; // Fallback
    const text = libelle.toLowerCase();
    // 1. Métaux Précieux (Or, Argent) -> Lingot / Valeur
    if (text.includes('or ') || text.includes('argent') || text.includes('platine')) {
      return 'monetization_on';
    }
    // 2. Chimie (Bain, Chlorure, Acide) -> Fiole
    if (text.includes('bain') || text.includes('chlorure') || text.includes('chimique')) {
      return 'science';
    }
    // 3. Incinération (A bruler) -> Feu
    if (text.includes('bruler') || text.includes('feu')) {
      return 'whatshot';
    }
    // 4. Résidus (Cendres, Balayures) -> Particules
    if (text.includes('cendre') || text.includes('poussiere')) {
      return 'grain'; // Effet de grain/poussière
    }
    // 5. Pierres (Desserti, Bijoux) -> Diamant
    if (text.includes('desserti') || text.includes('bijoux')) {
      return 'diamond';
    }
    // 6. Brut / Naturel (Natif, Minerai) -> Montagne
    if (text.includes('natif') || text.includes('minerai')) {
      return 'terrain';
    }
    // 7. Complexe / Recyclage -> Recyclage
    if (text.includes('complexe') || text.includes('dechets')) {
      return 'recycling';
    }
    // Par défaut
    return 'category';
  }
}
