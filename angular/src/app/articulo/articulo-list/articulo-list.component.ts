import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-articulo-list',
  templateUrl: './articulo-list.component.html',
  styleUrls: ['./articulo-list.component.scss']
})
export class ArticuloListComponent implements OnInit {

  constructor(private http: HttpClient) { }

  columnDefs = [
    {headerName: 'Modelo', field: 'modelo'},
    {headerName: 'Descripcion', field: 'descripcion'},
    {headerName: 'Clase', field: 'clase'},
    {headerName: 'Tipo', field: 'tipo'},
    {headerName: 'Marca', field: 'marca'},
    {headerName: 'Precio', field: 'precio'},
    {headerName: 'Cantidad', field: 'cantidad'}
  ];

  data: any;

  ngOnInit() {
    this.data = this.http.get('http://localhost:8080/proeza-sgs/rest/product/search?filter=CAN');
  }
}
