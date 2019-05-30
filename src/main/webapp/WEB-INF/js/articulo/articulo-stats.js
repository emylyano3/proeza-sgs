$(document).ready(function() {
//	$.ajax({
//		url : '/proeza-sgs/rest/articulo/stats/priceHistory/PRRS000001',
//		type : 'POST',
//		dataType : 'json',
//		contentType : 'application/json',
//		success: function (data, status, jqXHR) {
//			var ctx = document.getElementById("priceHistory").getContext("2d");
//			var chartData = {
//			    labels: data.labels,
//			    datasets: [
//			        {
//			            label: [[#{prod.chart.pricehistory.label}]],
//			            fillColor: 'rgba(110,220,110,0.3)',
//			            strokeColor: 'rgba(110,220,110,1)',
//			            pointColor: 'rgba(110,220,110,1)',
//			            pointStrokeColor: '#fff',
//			            pointHighlightFill: '#fff',
//			            pointHighlightStroke: 'rgba(220,220,220,1)',
//			            data: data.data
//			        }
//			    ]
//			};
//			var priceHistoryChart = new Chart(ctx).Line(chartData,{responsive: true});
//			$('#stock').text(data.stock);
//			$('#priceUpdates').text(data.updates);
//			$('#lastStockUpdate').text([[#{prod.chart.laststockupdate}]] + $.formatDateTime('mm/dd/yy', new Date(data.lastStockUpdate)));
//			$('#lastPriceUpdate').text([[#{prod.chart.lastpriceupdate}]] + $.formatDateTime('mm/dd/yy', new Date(data.lastPriceUpdate)));
//        },
//        error: function (jqXHR, status) {            
//			console.log(jqXHR.statusText);
//			console.err('Error: No se pudo obtener la data del grafico de historial de precio');
//        }
//	});
	$.ajax({
		url : product.rest.bestSellers + '/5',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		success: function (data, status, jqXHR) {
			var ctx = $('#bestsellersArticles').get(0).getContext('2d');
			var bestsellersArticlesChart = new Chart(ctx).Doughnut(data,{responsive: false});
        },
        error: function (jqXHR, status) {
			console.log(jqXHR.statusText);
			console.err('Error: No se pudo obtener la data de los articulos "best seller"');
        }
	});
	$.ajax({
		url : product.rest.worstSellers + '/5',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		success: function (data, status, jqXHR) {
			var ctx = $('#worstsellersArticles').get(0).getContext('2d');
			var worstsellersArticlesChart = new Chart(ctx).Doughnut(data,{responsive: false});
        },
        error: function (jqXHR, status) {            
			console.log(jqXHR.statusText);
			console.err('Error: No se pudo obtener la data de los articulo "worst seller"');
        }
	});
	$.ajax({
		url : product.rest.brandPresence + '/5',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		success: function (data, status, jqXHR) {
			var ctx = $('#brandPresence').get(0).getContext('2d');
			var brandPresenceChart = new Chart(ctx).Doughnut(data,{responsive: false});
        },
        error: function (jqXHR, status) {            
			console.log(jqXHR.statusText);
			console.err('Error: No se pudo obtener la data de presencia de marcas');
        }
	});
	$.ajax({
		url : product.rest.stockHistory + '/12',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		success: function (data, status, jqXHR) {
			var stockChartData = {
			    labels: data['STOCK'].labels,
			    datasets: [
			        {
			            label: /*[[#{prod.chart.stockhistory.label}]]*/ '',
			            fillColor: 'rgba(110,220,110,0.3)',
			            strokeColor: 'rgba(110,220,110,1)',
			            pointColor: 'rgba(110,220,110,1)',
			            pointStrokeColor: '#fff',
			            pointHighlightFill: '#fff',
			            pointHighlightStroke: 'rgba(220,220,220,1)',
			            data: data['STOCK'].data
			        }
			    ]
			};
			var ctx = $('#stockHistory').get(0).getContext('2d');
			var stockHistoryChart = new Chart(ctx).Bar(stockChartData, {responsive: false});
			var stockValueChartData = {
			    labels: data['CAPITAL'].labels,
			    datasets: [
			        {
			            label: /*[[#{prod.chart.stockhistory.label}]]*/ '',
			            fillColor: 'rgba(110,220,110,0.3)',
			            strokeColor: 'rgba(110,220,110,1)',
			            pointColor: 'rgba(110,220,110,1)',
			            pointStrokeColor: '#fff',
			            pointHighlightFill: '#fff',
			            pointHighlightStroke: 'rgba(220,220,220,1)',
			            data: data['CAPITAL'].data
			        }
			    ]
			};
			var ctx = $('#stockValueHistory').get(0).getContext('2d');
			var stockValueHistoryChart = new Chart(ctx).Line(stockValueChartData, {responsive: false});
			$('#lowestStock').text(data['STOCK'].lowestValue);
			$('#lowestStockDate').text(data['STOCK'].lowestValueDate);
			$('#highestStock').text(data['STOCK'].highestValue);
			$('#highestStockDate').text(data['STOCK'].highestValueDate);
			
			$('#lowestStockValue').text(data['CAPITAL'].lowestValue);
			$('#lowestStockValueDate').text(data['CAPITAL'].lowestValueDate);
			$('#highestStockValue').text(data['CAPITAL'].highestValue);
			$('#highestStockValueDate').text(data['CAPITAL'].highestValueDate);
        },
        error: function (jqXHR, status) {            
			console.log(jqXHR.statusText);
			console.err('Error: No se pudo obtener la data de presencia de rubros');
        }
	});
});