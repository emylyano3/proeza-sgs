import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridAngular } from 'ag-grid-angular';
import { ProvidedFilter } from 'ag-grid-community';

@Component({
  selector: 'app-articulo-list',
  templateUrl: './articulo-list.component.html',
  styleUrls: ['./articulo-list.component.scss']
})
export class ArticuloListComponent implements OnInit {
  @ViewChild('userListGrid', { static: false }) userListGrid: AgGridAngular;

  constructor(private http: HttpClient) { }

  columnDefs = [
    { headerName: 'Modelo', field: 'modelo', filter: true, sortable: true, editable: true, checkboxSelection: true },
    { headerName: 'Descripcion', field: 'descripcion', filter: true, sortable: true, editable: true },
    { headerName: 'Clase', field: 'clase', filter: true, sortable: true, editable: true },
    { headerName: 'Tipo', field: 'tipo', filter: true, sortable: true, editable: true },
    { headerName: 'Marca', field: 'marca', filter: true, sortable: true, editable: true },
    { headerName: 'Precio', field: 'precio', filter: true, sortable: true, editable: true },
    { headerName: 'Cantidad', field: 'cantidad', filter: true, sortable: true, editable: true }
  ];

  data: any;

  ngOnInit() {
    this.data = this.http.get('http://localhost:8080/proeza-sgs/rest/product/search?filter=wat');
  }

  getSelectedRows() {
    const selectedNodes = this.userListGrid.api.getSelectedNodes();
    const selectedData = selectedNodes.map(node => node.data);
    const selectedDataStringPresentation = selectedData.map(node => node.modelo + ' ' + node.marca).join(', ');
    alert(`Selected nodes: ${selectedDataStringPresentation}`);
  }

  getContextMenuItems(params) {
    var contextMenu = [];
    contextMenu.push({ name: 'Inform Sale' });
    contextMenu.push(
      {
        name: 'Quick Sale',
        subMenu: [
          {
            name: 'Cash',
            action: function () {
              console.log('Niall was pressed');
            },
          },
          {
            name: 'Debit',
            action: function () {
              console.log('Sean was pressed');
            },
          },
          {
            name: 'Credit',
            action: function () {
              console.log('John was pressed');
            },
          },
        ],
      }
    );
    contextMenu.push('separator');
    contextMenu.push('copy');
    contextMenu.push('chartRange');
    return contextMenu;
  }
}
