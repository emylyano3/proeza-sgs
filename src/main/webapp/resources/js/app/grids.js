function loadGridsData (){

	var url = "http://localhost:8080/proeza-sgs/rest/listarticulos";

    // prepare the data
    var source =
    {
        dataType: "json",
        dataFields: [
            { name: 'id', type: 'long' },
            { name: 'codigo', type: 'string' },
            { name: 'modelo', type: 'string' },
            { name: 'descripcion', type: 'string' },
            { name: 'rubro', type: 'string' },
            { name: 'clase', type: 'string' },
            { name: 'tipo', type: 'string' },
            { name: 'marca', type: 'string' },
            { name: 'costo', type: 'float' },
            { name: 'precio', type: 'float' },
            { name: 'cantidad', type: 'int' }
        ],
        id: 'id',
        url: url
    };

    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#dataTable").jqxDataTable(
    {
        width: 1050,
        pageable: true,
        pagerButtonsCount: 10,
        source: dataAdapter,
        columnsResize: true,
        columns: [
          { text: 'Id', dataField: 'id', width: 80 },
          { text: 'Código', dataField: 'codigo', width: 80 },
          { text: 'Modelo', dataField: 'modelo', width: 150 },
          { text: 'Descripción', dataField: 'descripcion', width: 230 },
          { text: 'Rubro', dataField: 'rubro', width: 80 },
          { text: 'Clase', dataField: 'clase', width: 80 },
          { text: 'Tipo', dataField: 'tipo', width: 80 },
          { text: 'Marca', dataField: 'marca', width: 80 },
          { text: 'Precio', dataField: 'costo', width: 70 },
          { text: 'Costo', dataField: 'precio', width: 70 },
          { text: 'Cantidad', dataField: 'cantidad' }
      ]
    });
};