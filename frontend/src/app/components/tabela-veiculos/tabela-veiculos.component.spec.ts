import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaVeiculosComponent } from './tabela-veiculos.component';

describe('TabelaVeiculosComponent', () => {
  let component: TabelaVeiculosComponent;
  let fixture: ComponentFixture<TabelaVeiculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaVeiculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TabelaVeiculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
