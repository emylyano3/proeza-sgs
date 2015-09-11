var menu = {
	rest : {
		lastMonthsSales : '/proeza-sgs/rest/sales/stats/userLastMonthsSales'
	}
}

function loadUserSalesSparkline(alias, months, barWidth) {
	if (document.getElementById('salesSparkline') != null) {
		$.ajax({
	        url : menu.rest.lastMonthsSales + '/' + alias + '/' + months,
	        type : 'POST',
	        dataType: 'json',
	        contentType : 'application/json',
	        success : function (data, status, jqXHR) {
	            var values = data.data;
	            // Sparkline bar chart data and options used under Profile image on left navigation panel
	            $("#salesSparkline").sparkline(values, {
	                type: 'bar',
	                barWidth: barWidth,
	                height: '30px',
	                barColor: '#62cb31',
	                negBarColor: '#53ac2a'
	            });
	        }
	    });
	}
}