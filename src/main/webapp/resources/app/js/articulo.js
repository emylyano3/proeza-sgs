var gridSource;
var currentRow = -1;
var formMode;

var product = {
	rest : {
		getImages : '/proeza-sgs/rest/product/getImages',
		addImage : '/proeza-sgs/rest/product/addImage',
		getImage : '/proeza-sgs/rest/product/getImage',
		getThumbnail : '/proeza-sgs/rest/product/getThumbnail',
		search : '/proeza-sgs/rest/product/search',
		create : '/proeza-sgs/rest/product/create',
		update : '/proeza-sgs/rest/product/update',
		remove : '/proeza-sgs/rest/product/remove',
		find : '/proeza-sgs/rest/product/find',
		quickSale : '/proeza-sgs/rest/venta/quickSale',
	}
}

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

function quickSale (productCode, saleType, user, notifyCallback) {
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
			notifyCallback(productCode, 'success');
		},
		error: function () {
			notifyCallback(productCode, 'error');
        }
	});
}

function findProduct (productCode, notifyCallback) {
	$.ajax({
		type : 'POST',
		url : product.rest.find,
		dataType: 'json',
		contentType: 'application/json',
		data : productCode,
		success : function(productData) {
			notifyCallback(productData, 'success');
		},
		error: function (productData) {
			notifyCallback(productData, 'error');
        }
	});
}