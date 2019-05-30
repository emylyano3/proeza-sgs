$(document).ready(function () {
	initImageGallery();
	configGridSource();
	configGrid();            
    configControls();
});

function initImageGallery() {
	$('#blueimp-gallery').data('useBootstrapModal', false);
    $('#blueimp-gallery').toggleClass('blueimp-gallery-controls', true);	
}

function configControls() {
	// Inicializa los controles del filtro
    $('#filter').on('input', function() {
        refreshGrid(gridSource, 'productGrid');
    });
    $('#eraser').click(function () {
        cleanContainer('links');
        $('#productGrid').jqxGrid('clear');
        $('#filter').val('');
    });	
}

function configGrid() {
    var dataAdapter = new $.jqx.dataAdapter(gridSource);
    // initializa la grilla de articulos
    $('#productGrid').jqxGrid({
        width: '100%',
		height : '100%',
		theme : 'energyblueproeza',
        source: dataAdapter,
		pageable : false,
        altrows: true,
        columnsresize: false,
        sortable: true,
        enablehover : false,
		enabletooltips : true,
        columns: [
			{ 
				text: /*[[#{prod.grid.model}]]*/ 'Modelo', 
				dataField: 'modelo', 
				width: '21%' 
			},{ 
				text: /*[[#{prod.grid.desc}]]*/ 'Descripción', 
				dataField: 'descripcion', 
				width: '21%' 
			},{ 
				text: /*[[#{prod.grid.clazz}]]*/ 'Clase', 
				dataField: 'clase',
				width: '14%' 
			},{ 
				text: /*[[#{prod.grid.type}]]*/ 'Tipo', 
				dataField: 'tipo', 
				width: '12%' 
			},{
				text: /*[[#{prod.grid.trademark}]]*/ 'Marca', 
				dataField: 'marca', 
				width: '14%' 
			},{
				text: /*[[#{prod.grid.price}]]*/ 'Precio', 
				dataField: 'precio', 
				cellsalign: 'right', 
				cellsformat: 'c2', 
				minwidth: '16%' 
			},{
				text: /*[[#{prod.grid.quantity}]]*/ 'Cantidad', 
				dataField: 'cantidad', 
				cellsalign: 'center', 
				minwidth: '2%' 
			}
        ]
    });
    var contextMenu = initGridContextMenu();
    $('#productGrid').on('rowselect', function (event) {
    	var rowIndex = event.args.rowindex;
    	var dataRecord = $('#productGrid').jqxGrid('getrowdata', rowIndex);
    	if (currentRow != rowIndex) {
    		currentRow = rowIndex;
    		cleanContainer('links');
    		updateGaleryPreview(dataRecord.codigo, 'links');
    	}
    });
	$('#productGrid').on('rowclick', function(event) {
		$('#productGrid').jqxGrid('selectrow', event.args.rowindex);
		if (event.args.rightclick) {
			var scrollTop = $(window).scrollTop();
			var scrollLeft = $(window).scrollLeft();
			contextMenu.jqxMenu(
					'open', 
					parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft,
					parseInt(event.args.originalEvent.clientY) + 5 + scrollTop
			);
			return false;
		}
	});
}

function initGridContextMenu() {
	// crea el menu contextual
	var contextMenu = $('#gridContextMenu').jqxMenu({
		width : 250,
		autoOpenPopup : false,
		mode : 'popup'
	});
	$('#productGrid').on('contextmenu', function() {
		return false;
	});
	// Manejo los eventos sobre el context menu de la grilla
	$('#gridContextMenu').on('itemclick', function(event) {
		var rowindex = $('#productGrid').jqxGrid('getselectedrowindex');
		var offset = $('#productGrid').offset();
		// get the clicked row's data and initialize the input fields.
		var dataRecord = $('#productGrid').jqxGrid('getrowdata', rowindex);
		var seeImages = /*[[#{menuitem.seeImages}]]*/ '';
		var quickCashSale = /*[[#{menuitem.quickCashSale}]]*/ '';
		var quickCreditSale = /*[[#{menuitem.quickCreditSale}]]*/ '';
		var quickDebitSale = /*[[#{menuitem.quickDebitSale}]]*/ '';
		var userAlias = /*[[${user.alias}]]*/ '';
		switch (event.target.id) {
			case seeImages:						
				blueimp.Gallery($('#links a'), $('#blueimp-gallery').data());
				break;
			case quickCashSale:
				quickSale (dataRecord.codigo, /*[[#{saletype.cash}]]*/ '', userAlias, notifyQuickSaleDone);
				break;
			case quickCreditSale:
				quickSale (dataRecord.codigo, /*[[#{saletype.credit}]]*/ '', userAlias, notifyQuickSaleDone);
				break;
			case quickDebitSale:
				quickSale (dataRecord.codigo, /*[[#{saletype.debit}]]*/ '', userAlias, notifyQuickSaleDone);
				break;
			default:
				break;
		}
	})
	return contextMenu;
}

function notifyQuickSaleDone (productCode, status) {
	var title, message, type;
	switch (status) {
		case 'success':
    		title = /*[[#{prod.quickSale.success.title}]]*/ ''; 
    		message = /*[[#{prod.quickSale.success.message}]]*/ '';
    		type = 'success';
    		refreshGrid(gridSource, 'productGrid');
		break;
		case 'error':
    		title = /*[[#{prod.quickSale.error.title}]]*/ ''; 
    		message = /*[[#{prod.quickSale.error.message}]]*/ '';
    		type = 'danger';
		break;
		default:
			break;
	}
	$.notify({
		title: title,
		message: message
	},{
		type: type
	});
}