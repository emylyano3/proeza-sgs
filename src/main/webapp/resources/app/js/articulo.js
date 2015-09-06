var articulo = {
	rest : {
		getImages : '/proeza-sgs/rest/articulo/getImages',
		addImage : '/proeza-sgs/rest/articulo/addImage',
		getImage : '/proeza-sgs/rest/articulo/getImage',
		getThumbnail : '/proeza-sgs/rest/articulo/getThumbnail',
		search : '/proeza-sgs/rest/articulo/search',
		create : '/proeza-sgs/rest/articulo/create',
		quickSale : '/proeza-sgs/rest/venta/quickSale',
	}
}

function updateGaleryPreview (productCode, containerId) {
	var imagesUrl = articulo.rest.getImages + '/' + productCode;
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
		            thumbnailUrl = articulo.rest.getThumbnail + '/' + productCode + '/' + result.id;
		            imageUrl = articulo.rest.getImage + '/' + productCode + '/' + result.id;
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

function quickSale (productCode, type, notifyCallback) {
	var saleData = {
		productCode : productCode,
		type : type
	};
	$.ajax({
		type : 'POST',
		url : articulo.rest.quickSale,
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