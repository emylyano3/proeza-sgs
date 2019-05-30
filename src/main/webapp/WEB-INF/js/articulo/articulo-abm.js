$(document).ready(function() {
	configGridSource();
	configProductGrid(); 
	configControls();				
});

function configControls() {
	$('#cbRubro').change(function(){
		var label = $("label[for='cbRubro']");
		relateCombos('#cbRubro', '#cbClase', product.rest.classesByCategory, '', function() {notifyComboError(label.text())} );
	});
	$('#cbClase').change(function(){
		var label = $("label[for='cbClase']");
		relateCombos('#cbClase', '#cbTipo', product.rest.typesByClass, '', function() {notifyComboError(label.text())} );
	});
	$('#newProduct').click(function(){
		initCreationForm();
	});
	$('#productForm').submit(function( e ) {
	    e.preventDefault();
	    switch (formMode) {
    	case 'creation' : 
			addProduct();
			break;
    	case 'edition' :
			if (currentRow >= 0) {
				updateProduct();
			}
			break;
		default:
			break;
		}
		hideAuxPanel();
	});

	// Hide popup when cancel
	$('#cancelEdit').click(function() {
		hideAuxPanel();
	});
	$('#savePriceChange').click(function() {
		console.log("Saving price change");
	});
	// Hide popup when cancel
	$('#cancelPriceChange').click(function() {
		hideAuxPanel();
	});
	$('#cancelImage').click(function() {
		$('#addImagePopup').modal('hide');
	});

	// Configuracion del control de ingreso del precio
	$('#precio').inputmask('currency', {
		autoUnmask : true, rightAlign: false 
	});
	$('#costo').inputmask('currency', {
		autoUnmask : true, rightAlign: false 
	});
	$('#percentage').inputmask('99');
	
	// Inicializa los controles del filtro
    $('#filter').on('input', function() {
    	refreshGrid(gridSource, 'productGrid');
    });
    $('#eraser').click(function () {
        $('#productGrid').jqxGrid('clear');
        cleanContainer('links');
    });
	$('#confirmAction').click(function() {
		removeProduct();
	});
}

function notifyComboError (comboName) {
	$.notify({
		message: /*[[#{rest.dataload.error}]]*/ ''
	},{
		type: 'danger'
	});
}

function configProductGrid () {
	var dataAdapter = new $.jqx.dataAdapter(gridSource);
	// initializa la grilla de articulos
	$('#productGrid').jqxGrid({
		width : '100%',
		height : '100%',
		theme : 'energyblueproeza',
		source : dataAdapter,
		pageable : false,
		altrows : true,
        columnsresize: false,
		sortable : true,
		enablehover : false,
		enabletooltips : true,
		selectionmode: 'multiplerowsadvanced',
		columns : [ {
				text : /*[[#{prod.grid.model}]]*/ 'Modelo',
				dataField : 'modelo',
				width : '26%'
			},{
				text : /*[[#{prod.grid.clazz}]]*/ 'Clase',
				dataField : 'clase',
				width : '26%'
			},{
				text : /*[[#{prod.grid.trademark}]]*/ 'Marca',
				dataField : 'marca',
				width : '20%'
			},{
				text : /*[[#{prod.grid.cost}]]*/ 'Costo',
				dataField : 'costo',
				cellsalign : 'right',
				cellsformat : 'c2',
				minwidth : '13%'
			},{
				text : /*[[#{prod.grid.price}]]*/ 'Precio',
				dataField : 'precio',
				cellsalign : 'right',
				cellsformat : 'c2',
				minwidth : '13%'
			},{
				text: /*[[#{prod.grid.quantity}]]*/ 'Cantidad', 
				dataField: 'cantidad', 
				cellsalign: 'center', 
				minwidth: '2%' 
			}
		]
	});
	// create context menu
	var singleProductMenu = $('#contextSingleProductMenu').jqxMenu({
		width : 190,
		autoOpenPopup : false,
		mode : 'popup'

	});
	// create context menu
	var multiProductMenu = $('#contextMultiProductMenu').jqxMenu({
		width : 190,
		autoOpenPopup : false,
		mode : 'popup'

	});
	$('#productGrid').on('contextmenu', function() {
		return false;
	});
	// handle context menu clicks.
	$('#contextSingleProductMenu').on('itemclick', function(event) {
        event.preventDefault();
		var args = event.args;
		var currentRow = $('#productGrid').jqxGrid('getselectedrowindex');
		var offset = $('#productGrid').offset();
		var dataRecord = $('#productGrid').jqxGrid('getrowdata', currentRow);
		var command = $.trim($(args).text());
		var edit = /*[[#{prod.menu.edit}]]*/ 'Edit';
		var create = /*[[#{prod.menu.create}]]*/ 'Create';
		var copy = /*[[#{prod.menu.copy}]]*/ 'Copy';
		var remove = /*[[#{prod.menu.remove}]]*/ 'Remove';
		var addImage = /*[[#{prod.menu.addImage}]]*/ 'Add Image';
		switch (command) {
		case edit:
			initEditionForm(dataRecord);
			break;
		case create:
			initCreationForm();
			break;
		case copy:
			initCopyCreationForm(dataRecord);
			break;
		case remove:
			showRemoveConfirmation();
			break;
		case addImage:
			showLoadImagePopup(dataRecord);
			break;
		default:
			break;
		}
	});
	// handle context menu clicks.
	$('#contextMultiProductMenu').on('itemclick', function(event) {
        event.preventDefault();
		var args = event.args;
		var rowsSelected = $('#productGrid').jqxGrid('selectedrowindexes');
		$.each(rowsSelected, function(index, row) {
			var dataRecord = $('#productGrid').jqxGrid('getrowdata', row);
			
		});
		var command = $.trim($(args).text());
		var updatePrice = /*[[#{prod.menu.updatePrice}]]*/ 'Update price';
		switch (command) {
		case updatePrice:
			showAuxPanel('#priceChangePanel');
			break;
		default:
			break;
		}
	});
	// Procesa cualquier clic hecho sobre la grilla de articulos
	$('#productGrid').on('rowclick', function (event) {
		var rowIndex = event.args.rowindex;
		var rowsSelected = $('#productGrid').jqxGrid('selectedrowindexes');
		if (rowsSelected.length > 1) {
			// El comportamiento de la grilla varia dependiendo de si hay más de una row seleccionada
			if (event.args.rightclick) {
				if ($.inArray(rowIndex, rowsSelected) >= 0) {
					// Aca va el manejo del menu contextual para multiples items seleccionados
					/*
					multiProductMenu.jqxMenu(
						'open', 
						parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft,
						parseInt(event.args.originalEvent.clientY) + 5 + scrollTop
					);
					*/
					return false;
				} else {
					$('#productGrid').jqxGrid('clearselection');
				}
			}
		}
		if (event.args.rightclick) {
			/* Solo se selecciona la row sobre la que se hizo el clic derecho cuando no hay nada seleccionado.
			 * Esto es para evitar que cuando hay alguna fila seleccionada, si el usuario hace clic derecho se 
			 * seleccione accidentalmente la fila sobre donde se hizo el clic. */
			$('#productGrid').jqxGrid('clearselection');
			$('#productGrid').jqxGrid('selectrow', rowIndex);
			var scrollTop = $(window).scrollTop();
			var scrollLeft = $(window).scrollLeft();
			singleProductMenu.jqxMenu(
				'open', 
				parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft,
				parseInt(event.args.originalEvent.clientY) + 5 + scrollTop
			);
		}
    	var dataRecord = $('#productGrid').jqxGrid('getrowdata', rowIndex);
    	if (currentRow != rowIndex) {
    		currentRow = rowIndex;
    		cleanContainer('links');
    		updateGaleryPreview(dataRecord.codigo, 'links');
    	}
		return false;
	});
}

function getFormData () {
	return  {
		codigo : $('#codigo').val(),
		modelo : $('#modelo').val(),
		descripcion : $('#descripcion').val(),
		clase : $('#cbClase').val(),
		tipo : $('#cbTipo').val(),
		rubro : $('#cbRubro').val(),
		marca : $('#cbMarca').val(),
		precio : $('#precio').val(),
		costo : $('#costo').val(),
		cantidad : $('#cantidad').val(),
		codClase : $('#cbClase option:selected').attr('value'),
		codTipo : $('#cbTipo option:selected').attr('value'),
		codMarca : $('#cbMarca option:selected').attr('value'),
		codRubro : $('#cbRubro option:selected').attr('value')
	};	
}

function updateProduct () {
	var rowData = getFormData();
	var rowid = $('#productGrid').jqxGrid('getrowid', currentRow);
	$.ajax({
		type : 'POST',
		url : product.rest.update,
		dataType: 'json',
		contentType: 'application/json',
		data : JSON.stringify(rowData),
		success : function() {
			$.notify({
				title: /*[[#{prod.update.title}]]*/ '<strong>Modificación de artículo:</strong>',
				message: /*[[#{prod.update.success.message}]]*/ 'La modificación se realizó correctamente'
			},{
				type: 'success'
			});
			refreshGrid(gridSource, 'productGrid');
		},
		error: function () {            
			$.notify({
				title: /*[[#{prod.update.title}]]*/ '<strong>Modificación de artículo:</strong>',
				message: /*[[#{prod.update.error.message}]]*/ 'Ocurrió un error en la actualización del artículo'
			},{
				type: 'danger'
			});
        }
	});
}

function addProduct () {
	var formData = getFormData();
	formData.codigo = '';
   	$.ajax({
		type : 'POST',
		url : product.rest.create,
		dataType: 'json',
		contentType: 'application/json',
		data : JSON.stringify(formData),
		success : function() { 
			$.notify({
				title: /*[[#{prod.create.title}]]*/ '<strong>Creación de artículo:</strong>',
				message: /*[[#{prod.create.success.message}]]*/ 'El articulo fue creado correctamente' 
			},{
				type: 'success'
			});
			refreshGrid(gridSource, 'productGrid');
		},
		error: function () {            
			$.notify({
				title: /*[[#{prod.create.title}]]*/ '<strong>Creación de artículo:</strong>',
				message: /*[[#{prod.create.error.message}]]*/ 'Ocurrió un error durante la creación del artículo',
			},{
				type: 'danger'
			});
        }
	});
}

function showLoadImagePopup(dataRecord) {
	var fileInputOptions = {
		language: /*[[#{app.language}]]*/ 'es',
		showUpload : true,
		showRemove : true,
	    uploadAsync: true,
		maxFileCount : 5,
        allowedFileExtensions : ['jpg', 'png'],
		allowedFileTypes : ['image'],
        uploadUrl: product.rest.addImage + '?artCode=' + dataRecord.codigo
	};
 	// File input plugin options
    $('#uploadImage').fileinput(fileInputOptions);
	$('#addImagePopup').modal('show');	
}

function showRemoveConfirmation () {
	swal({
			title: /*[[#{control.confirmDelete.title}]]*/ '',
			text: /*[[#{prod.confirm.remove}]]*/ '',
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: /*[[#{control.confirmDelete}]]*/ '',
			cancelButtonText: /*[[#{control.cancelDelete}]]*/ ''
		}, 
		function (isConfirm) {
			if (isConfirm) {
				removeProduct();
			} 
		}
	);
}

function removeProduct () {
	var rowdata = $('#productGrid').jqxGrid('getrowdata', currentRow);
	var rowid = $('#productGrid').jqxGrid('getrowid', currentRow);
	$.ajax({
		type : 'POST',
		url : product.rest.remove,
		dataType: 'json',
		contentType: 'application/json',
		data : rowdata.codigo,
		success : function() {
			$.notify({
				title: /*[[#{prod.remove.title}]]*/ '<strong>Eliminación de artículo:</strong>',
				message: /*[[#{prod.remove.success.message}]]*/ 'La eliminación se realizó correctamente'
			},{
				type: 'success'
			});
			$('#productGrid').jqxGrid('deleterow', rowid);	
		},
		error: function () {            
			$.notify({
				title: /*[[#{prod.remove.title}]]*/ '<strong>Eliminación de artículo:</strong>',
				message: /*[[#{prod.remove.error.message}]]*/ 'Ocurrió un error en la eliminación del artículo'
			},{
				type: 'danger'
			});
        }
	});
}
var currentAuxPanel;

function showAuxPanel(panel) {
	$('#gridPanel').hide();
	$('#commandBar').hide();
	$('#thumbnailsPanel').hide();
	currentAuxPanel = panel;
	$(panel).show();
}

function hideAuxPanel() {
	$('#gridPanel').show();
	$('#commandBar').show();
	$('#thumbnailsPanel').show();
	$(currentAuxPanel).hide();
}

function initCreationForm () {
	formMode = 'creation';
	$('#modelo').val('');
	$('#descripcion').val('');
	$('#cbClase').val('');
	$('#cbTipo').val('');
	$('#cbMarca').val('');
	$('#cbRubro').val('');
	$('#precio').val('0');
	$('#costo').val('0');
	$('#cantidad').val('0');
	// Setting the form controls
	$('#formTitle').text(/*[[#{prod.admin.creation.title}]]*/ '');
	$('#formFooter').text(/*[[#{prod.admin.creating}]]*/ '');
	$('#codigo').hide();
	$('#lblCodigo').hide();
	showAuxPanel('#editionPanel');
}

function initCopyCreationForm (dataRecord) {
	formMode = 'creation';
	$('#codigo').val('');
	$('#codigo').hide();
	$('#lblCodigo').hide();
	$('#modelo').val(dataRecord.modelo);
	$('#descripcion').val(dataRecord.descripcion);
	$('#cbMarca').val(dataRecord.codMarca);
	$('#cbRubro').val(dataRecord.codRubro);
	$('#cbRubro').trigger('chain', dataRecord);
	$('#precio').val(dataRecord.precio);
	$('#costo').val(dataRecord.costo);
	$('#cantidad').val('0');
	// Setting the form controls
	$('#formTitle').text(/*[[#{prod.admin.creation.title}]]*/ '');
	var footerText = /*[[#{prod.admin.copying}]]*/ '';
	$('#formFooter').text(footerText + dataRecord.clase + ' ' + dataRecord.marca + ' ' + dataRecord.modelo);
	$('#codigo').hide();
	$('#lblCodigo').hide();
	showAuxPanel('#editionPanel');
}

function initEditionForm (dataRecord) {
	formMode = 'edition';
	findProduct(dataRecord.codigo, findProductCallback);
	$('#formTitle').text(/*[[#{prod.admin.edition.title}]]*/ '');
	var footerText = /*[[#{prod.admin.editing}]]*/ '';
	$('#formFooter').text(footerText + dataRecord.codigo);
	$('#codigo').show();
	$('#lblCodigo').show();	
	showAuxPanel('#editionPanel');
}

function findProductCallback (productData, status) {
	switch (status) {
	case 'success':
		fillEditionForm(productData);
	break;
	case 'error':
		$.notify({
			title: /*[[#{prod.find.title}]]*/ '',
			message: /*[[#{prod.find.error.message}]]*/ '' 
		},{
			type: 'error'
		});
	break;
	default:
		break;
	}
}

$('#cbRubro').on('chain',  function(event, dataRecord) {
	chainCombos(
		'#cbRubro', 
		'#cbClase', 
		dataRecord.codClase, 
		product.rest.classesByCategory, 
		function () {
			chainCombos(
				'#cbClase',
				'#cbTipo',
				dataRecord.codTipo,
				product.rest.typesByClass
			)
		}
	);
   });

function fillEditionForm(dataRecord) {
	$('#codigo').val(dataRecord.codigo);
	$('#modelo').val(dataRecord.modelo);
	$('#descripcion').val(dataRecord.descripcion);
	$('#cbMarca').val(dataRecord.codMarca);
	$('#cbRubro').val(dataRecord.codRubro);
	$('#cbRubro').trigger('chain', dataRecord);
	$('#precio').val(dataRecord.precio);
	$('#costo').val(dataRecord.costo);
	$('#cantidad').val(dataRecord.cantidad);
}