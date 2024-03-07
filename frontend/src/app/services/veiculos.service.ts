import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from '../models/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  url = `http://localhost:8080/vehicle`

  constructor(private http: HttpClient) { }

  public getAllVehicle(): Observable<any> {
    return this.http.get<any>(
      this.url
    )
  }

  public getAllVehiclesWithParameters(make: string, releaseYear: number, color: string): Observable<any> {
    const params = {
      make: make,
      releaseYear: releaseYear,
      color: color
    }

    return this.http.get(this.url, {params})
  }

  public getCountUnsoldVehicles(): Observable<any> {
    return this.http.get<any>(
      this.url.concat("/unsold")
    )
  }

  public getCountVehiclesByDecade(decade: number): Observable<any> {
    return this.http.get<any>(
      this.url.concat(`/decade/${decade}`)
    )
  }

  public getCountByMake(make: string): Observable<any> {
    return this.http.get<any>(
      this.url.concat(`/make/${make}`)
    )
  }

  public getAllRegisteredVehiclesAtLastWeek(): Observable<any> {
    return this.http.get<any>(
      this.url.concat("/lastweek")
    )
  }

  public post(vehicle: any) {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(vehicle);
    console.log(vehicle)
    return this.http.post(this.url, body, { 'headers': headers })
  }

  public remove(id: number) {
    return this.http.delete(`${this.url}/${id}`)
  }

  public updateMember(id:number, vehicle: any): Observable<Object> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(vehicle);
    return this.http.put(`${this.url}/${id}`, body, { 'headers': headers })
  }

  updateVehicleByPatch(id: number, data: any): Observable<any> {
    const url = `${this.url}/${id}`;
    return this.http.patch(url, data);
  }

}
