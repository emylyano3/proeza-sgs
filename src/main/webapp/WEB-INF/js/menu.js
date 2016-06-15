var menu = {
	rest : {
		lastMonthsSales : '/proeza-sgs/rest/sales/stats/userLastMonthsSales'
	}
}
$(document).ready(function() {
	if (document.getElementById('salesSparkline') != null && document.getElementById('userAlias') != null) {
		$.ajax({
	        url : menu.rest.lastMonthsSales + '/' + $("#userAlias").text() + '/' + 12,
	        type : 'POST',
	        dataType: 'json',
	        contentType : 'application/json',
	        success : function (data, status, jqXHR) {
	            var values = data.data;
	            // Sparkline bar chart data and options used under Profile image on left navigation panel
	            $("#salesSparkline").sparkline(values, {
	                type: 'bar',
	                barWidth: 7,
	                height: '30px',
	                barColor: '#62cb31',
	                negBarColor: '#53ac2a'
	            });
	        }
	    });
	}
});