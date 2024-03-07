import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Vehicle } from 'src/app/models/Vehicle';
import { VeiculosService } from 'src/app/services/veiculos.service';

@Component({
  selector: 'app-tabela-veiculos',
  templateUrl: './tabela-veiculos.component.html',
  styleUrls: ['./tabela-veiculos.component.scss']
})
export class TabelaVeiculosComponent implements OnInit {

  vehicle:any = []
  mostrar: Boolean = false
  buttonValue = ''
  id: number = 0
  update: Boolean = false

  vehiclesUnsold: number = 0
  vehicleByMake: string = ''
  vehicleByMakeDisplayed: number = 0
  vehicleByDecade: number = 0
  vehicleByDecadeDisplayed: number = 0

  vehicleParameterMake: string = ''
  vehicleParameterReleaseYear: number = 0
  vehicleParameterColor: string = ''

  public member: any = {
    model: '',
    make: '',
    releaseYear: '',
    color: '',
    sold: '',
  }
  public member1: any = {
    model: '',
    make: '',
    releaseYear: '',
    color: '',
    sold: '',
  }

  vehicleForm!: FormGroup

  constructor(private veiculosService: VeiculosService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getAllVehicle()
    this.getCountUnsoldVehicles()
    
    this.vehicleForm = this.formBuilder.group({
      model: new FormControl('', [Validators.required]),
      make: new FormControl('', [Validators.required]),
      releaseYear: new FormControl('', [Validators.required]),
      color: new FormControl('', [Validators.required]),
    })
  }
  
  getAllVehicle() {
    this.veiculosService.getAllVehicle().subscribe(
      res => {
        this.vehicle = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  getAllVehiclesWithParameters() {
    this.veiculosService.getAllVehiclesWithParameters(this.vehicleParameterMake, this.vehicleParameterReleaseYear, this.vehicleParameterColor).subscribe(
      res => {
        this.vehicle = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  getCountUnsoldVehicles() {
    this.veiculosService.getCountUnsoldVehicles().subscribe(
      res => {
        this.vehiclesUnsold = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  getCountVehiclesByDecade() {
    this.veiculosService.getCountVehiclesByDecade(this.vehicleByDecade).subscribe(
      res => {
        this.vehicleByDecadeDisplayed = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  getCountByMake() {
    this.veiculosService.getCountByMake(this.vehicleByMake).subscribe(
      res => {
        this.vehicleByMakeDisplayed = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  getAllRegisteredVehiclesAtLastWeek() {
    this.veiculosService.getAllRegisteredVehiclesAtLastWeek().subscribe(
      res => {
        this.vehicle = res.data
      },
      erro => {
        console.log(erro)
      }
    )
  }

  addVehicle() {
    this.veiculosService.post({ model: this.vehicleForm.value.model, make: this.vehicleForm.value.make,releaseYear: this.vehicleForm.value.releaseYear, color: this.vehicleForm.value.color  }).subscribe(
      res => {
        this.getAllVehicle()
      }
    )
  }

  deleteVehicle(id: any) {
    this.veiculosService.remove(id).subscribe(
      res => {
        this.getAllVehicle()
      }
    )
  }

  editVehicle() {
    if (this.verificaApenasUmVazio() > 0 ) {
      console.log('patch')
      console.log(this.id)
      this.veiculosService.updateVehicleByPatch(this.id, { model: this.member1.model, make: this.member1.make, releaseYear: this.member1.releaseYear,color: this.member1.color, sold: this.member1.sold}).subscribe({
        next: (res: any) => {
          this.getAllVehicle()
          this.update = false
          console.log(res)
        }
      })
    } else {
      console.log('put')
      this.veiculosService.updateMember(this.id, { model: this.member1.model, make: this.member1.make, releaseYear: this.member1.releaseYear,color: this.member1.color, sold: this.member1.sold}).subscribe({
        next: (res: any) => {
          this.getAllVehicle()
          this.update = false
          console.log(res)
        }
      })
    }
  }

  verificaApenasUmVazio(): number {
    let countVazios = 0

    for (const key in this.member1) {
      if (this.member1.hasOwnProperty(key) && this.member1[key] === '') {
        countVazios++;
      }
    }

    return countVazios;
  }

  openModal(
    id: any,
    model: string, 
    make: string, 
    releaseYear: number,  
    color: string, 
    sold: boolean,) {
    this.id = id;
    this.member = {
      model: model,
      make: make,
      releaseYear: releaseYear,
      color: color,
      sold: sold,
    }
    this.update = true
  }
}
