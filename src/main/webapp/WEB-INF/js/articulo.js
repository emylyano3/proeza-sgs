var gridSource;
var currentRow = -1;
var formMode;

var baseRest = '/proeza-sgs/rest/product';

var product = {
	rest : {
		getImages : baseRest + '/getImages',
		addImage : baseRest + '/addImage',
		getImage : baseRest + '/getImage',
		getThumbnail : baseRest + '/getThumbnail',
		search : baseRest + '/search',
		create : baseRest + '/create',
		update : baseRest + '/update',
		remove : baseRest + '/remove',
		brandByCode : baseRest + '/brandByCode',
		addBrand : baseRest + '/addBrand',
		updateBrand : baseRest + '/updateBrand',
		deleteBrand : baseRest + '/deleteBrand',
		getBrands : baseRest + '/getBrands',
		addType : baseRest + '/addType',
		typesByClass : baseRest + '/typesByClass',
		updateType : baseRest + '/updateType',
		deleteType : baseRest + '/deleteType',
		getTypes : baseRest + '/getTypes',
		typeByCode : baseRest + '/typeByCode',
		classByCode : baseRest + '/classByCode',
		classesByCategory : baseRest + '/classesByCategory',
		updateClass : baseRest + '/updateClass',
		deleteClass : baseRest + '/deleteClass',
		getClasses : baseRest + '/getClasses',
		addClass : baseRest + '/addClass',
		categoryByCode : baseRest + '/categoryByCode',
		addCategory : baseRest + '/addCategory',
		updateCategory : baseRest + '/updateCategory',
		deleteCategory : baseRest + '/deleteCategory',
		getCategories : baseRest + '/getCategories',
		find : baseRest + '/find',
		quickSale : '/proeza-sgs/rest/venta/quickSale',
		bestSellers : baseRest + '/stats/bestSellers',
		worstSellers : baseRest + '/stats/worstSellers',
		brandPresence : baseRest + '/stats/brandPresence',
		stockHistory : baseRest + '/stats/stockHistory',
	}
};

function configGridSource() {
	gridSource = {
		datatype : 'json',
		id : 'id',
		url : product.rest.search + '?filter=',
		dataFields : [
           {name : 'codigo', type : 'string'},
           {name : 'modelo', type : 'string'},
           {name : 'descripcion', type : 'string' },
           {name : 'clase', type : 'string'},
           {name : 'tipo', type : 'string'},
           {name : 'marca', type : 'string'},
           {name : 'rubro', type : 'string'},
           {name : 'precio', type : 'double'},
           {name : 'costo', type : 'double'},
           {name : 'cantidad', type : 'int'},
           {name : 'codClase', type : 'string'},
           {name : 'codTipo', type : 'string'},
           {name : 'codMarca', type : 'string'},
           {name : 'codRubro', type : 'string'},
        ]
	};
}

function updateGaleryPreview (productCode, containerId) {
	var imagesUrl = product.rest.getImages + '/' + productCode;
	var linksContainer = $("#" + containerId);
    // Add the demo images as links with thumbnails to the page
    $.ajax({
		type : 'POST',
        url: imagesUrl,
        dataType: 'json'
    }).done(
    	function (result) {
	        var thumbnailUrl, imageUrl;
	        $.each(result,
	        	function (index, result) {
		            thumbnailUrl = product.rest.getThumbnail + '/' + productCode + '/' + result.id;
		            imageUrl = product.rest.getImage + '/' + productCode + '/' + result.id;
		            $('<a/>')
		                .append($('<img/>')
						.prop('src', thumbnailUrl))
		                .prop('href', imageUrl)
		                .prop('title', result.nombre)
		                .attr('data-gallery', '')
		                .appendTo(linksContainer);
		        }
	        );
	    }
    );
}

function cleanContainer(containerId) {
    var linksContainer = document.getElementById(containerId);
	while (linksContainer.hasChildNodes()) {
		linksContainer.removeChild(linksContainer.lastChild);
	}
}

function refreshGrid(source, gridName) {
	var filter = $('#filter').val();
    var params = 'filter=' + replaceAll(filter.trim(), ' ',',');
    source.url = product.rest.search + '?' + params;
	/*
	 * pasando "cells" al comando 'updatebounddata' solo se refrescan los
	 * valores de las celdas cuando la cantidad nueva de filas es igual a la
	 * anterior
	 */
	$('#' + gridName).jqxGrid('updatebounddata', 'cells');
}

function quickSale (productCode, saleType, user, callback) {
	var saleData = {
		productCode : productCode,
		saleType : saleType,
		user : user
	};
	$.ajax({
		type : 'POST',
		url : product.rest.quickSale,
		dataType: 'json',
		contentType: 'application/json',
		data : JSON.stringify(saleData),
		success : function() {
			if (callback) {
				callback(productCode, 'success');
			}
		},
		error: function () {
			if (callback) {
				callback(productCode, 'error');
			}
        }
	});
}

function findProduct (productCode, callback) {
	$.ajax({
		type : 'POST',
		url : product.rest.find,
		dataType: 'json',
		contentType: 'application/json',
		data : productCode,
		success : function(productData) {
			if (callback) {
				callback(productData, 'success');
			}
		},
		error: function (productData) {
			if (callback) {
				callback(productData, 'error');
			}
        }
	});
}


function loadEntityData(inputData, restUrl, errorMessage, setData) {
	$.ajax({
		type : 'POST',
		url : restUrl,
		dataType: 'json',
		contentType: 'application/json',
		data : inputData,
		success : function(data) {
			setData(data);
		},
		error : function(jqXHR, status) {
			$.notify({
				message: errorMessage
			},{
				type: 'danger'
			});
		}
	});
}	

function saveEntityData(data, restUrl, successMsg, callback) {
	$.ajax({
		type : 'POST',
		url : restUrl,
		dataType: 'json',
		contentType: 'application/json',
		data : JSON.stringify(data),
		success : function() {
			$.notify({
				message: successMsg
			},{
				type: 'success'
			});
			if (callback) {
				callback();
			}
		},
		error : function(jqXHR, status, errorThrown) {
			$.notify({
				message: JSON.parse(jqXHR.responseText).message
			},{
				type: 'danger'
			});
		}
	});
}