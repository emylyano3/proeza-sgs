$(document).ready(function() {
	updateSideBarStatus();

	// Add special class to minimalize page elements when screen is less than 768px
	setBodySmall();

	// Initialize metsiMenu plugin to sidebar menu
	// if (document.getElementById('side-menu') != null) {
	// 	$('#side-menu').metisMenu();
	// }

	// Initialize iCheck plugin
	// $('.i-checks').iCheck({
	// 	checkboxClass : 'icheckbox_square-green',
	// 	radioClass : 'iradio_square-green',
	// });

	// Initialize animate panel function
	$('.animate-panel').animatePanel();

	// Function for collapse hpanel
	$('.showhide').click(function(event) {
		event.preventDefault();
		var hpanel = $(this).closest('div.hpanel');
		var icon = $(this).find('i:first');
		var body = hpanel.find('div.panel-body');
		var footer = hpanel.find('div.panel-footer');
		body.slideToggle(300);
		footer.slideToggle(200);

		// Toggle icon from up to down
		icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
		hpanel.toggleClass('').toggleClass('panel-collapse');
		setTimeout(function() {
			hpanel.resize();
			hpanel.find('[id^=map-]').resize();
		}, 50);
	});

	// Function for close hpanel
	$('.closebox').click(function(event) {
		event.preventDefault();
		var hpanel = $(this).closest('div.hpanel');
		hpanel.remove();
	});

	// Function for small header
	$('.small-header-action').click(function(event) {
		event.preventDefault();
		var icon = $(this).find('i:first');
		var breadcrumb = $(this).parent().find('#hbreadcrumb');
		$(this).parent().parent().parent().toggleClass('small-header');
		breadcrumb.toggleClass('m-t-lg');
		icon.toggleClass('fa-arrow-up').toggleClass('fa-arrow-down');
	});

	// Set minimal height of #wrapper to fit the window
	fixWrapperHeight();

	// Initialize tooltips
	$('.tooltip-demo').tooltip({
		selector : '[data-toggle=tooltip]'
	});

	// Initialize popover
	$('[data-toggle=popover]').popover();

	// Move modal to body
	// Fix Bootstrap backdrop issu with animation.css
	$('.modal').appendTo('body');
});

function relateCombos(srcCombo, targetCombo, serviceUrl, tagetSelectedValue, notifyCallback) {
	var code = $(srcCombo + ' option:selected').attr('value');
	var optDefault = $(targetCombo + ' option:first-child');
	if (!code) {
		$(targetCombo).empty();
		$(targetCombo).append(optDefault);
		return;
	}
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		dataType: 'json',
		contentType: 'application/json',
		data : code,
		success : function(options) {
			$(targetCombo).empty();
			$(targetCombo).append(optDefault);
			$.each(options, function(index, option) {
			    $(targetCombo).append(new Option(option.nombre, option.codigo));
			});
			if (tagetSelectedValue) {
				$(targetCombo).val(tagetSelectedValue);
			}
		},
		error: function () {
			if (notifyCallback) {
				notifyCallback();
			}
        }
	});
}

function chainCombos(srcCombo, targetCombo, tagetSelectedValue, serviceUrl, chainedAction) {
	var code = $(srcCombo + ' option:selected').attr('value');
	var optDefault = $(targetCombo + ' option:first-child');
	if (!code) {
		$(targetCombo).empty();
		$(targetCombo).append(optDefault);
		return;
	}
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		dataType: 'json',
		contentType: 'application/json',
		data : code,
		success : function(options) {
			$(targetCombo).empty();
			$(targetCombo).append(optDefault);
			$.each(options, function(index, option) {
				$(targetCombo).append(new Option(option.nombre, option.codigo));
			});
			if (tagetSelectedValue) {
				$(targetCombo).val(tagetSelectedValue);
			}
			if (chainedAction) {
				chainedAction();
			}
		},
		error: function () {
			console.error('Error relacionando los combos [' + srcCombo + '] y [' + targetCombo + ']');
		}
	});
}

function emptyCombo (targetCombo, hasDefault) {
	var optDefault = $(targetCombo + ' option:first-child');
	$(targetCombo).empty();
	if (hasDefault && optDefault != null) {
		$(targetCombo).append(optDefault);
		$(targetCombo).val('');
	}
}

function initCombo(targetCombo, serviceUrl, notifyCallback) {
	var optDefault = $(targetCombo + ' option:first-child');
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		dataType: 'json',
		contentType: 'application/json',
		success : function(options) {
			$(targetCombo).empty();
			$(targetCombo).append(optDefault);
			$.each(options, function(index, option) {
				$(targetCombo).append(new Option(option.nombre, option.codigo));
			});
		},
		error: function () {
			if (notifyCallback) {
				notifyCallback();
			}
		}
	});
}

$(window).bind('load', function() {
	// Remove splash screen after load
	$('.splash').css('display', 'none');
});

$(window).bind('resize click', function() {

	// Add special class to minimalize page elements when screen is less than 768px
	setBodySmall();

	// Waint until metsiMenu, collapse and other effect finish and set wrapper height
	setTimeout(function() {
		fixWrapperHeight();
	}, 300);
});

function replaceAll(text, busca, reemplaza) {
	while (text.toString().indexOf(busca) != -1) {
		text = text.toString().replace(busca, reemplaza);
	}
	return text;
}

function fixWrapperHeight() {

	// Get and set current height
	var headerH = 62;
	var navigationH = $('#navigation').height();
	var contentH = $('.content').height();

	// Set new height when contnet height is less then navigation
	if (contentH < navigationH) {
		$('#wrapper').css('min-height', navigationH + 'px');
	}

	// Set new height when contnet height is less then navigation and navigation is less then window
	if (contentH < navigationH && navigationH < $(window).height()) {
		$('#wrapper').css('min-height', $(window).height() - headerH + 'px');
	}

	// Set new height when contnet is higher then navigation but less then window
	if (contentH > navigationH && contentH < $(window).height()) {
		$('#wrapper').css('min-height', $(window).height() - headerH + 'px');
	}
}

/**
 * Actualiza el estado de la barra de menu lateral. Adicionalmente refresca las
 * grilla de la pantalla en caso de existir alguna.
 */
function updateSideBarStatus() {
	$('.hide-menu').click(
		function(event) {
			event.preventDefault();
			if ($(window).width() < 769) {
				$('body').toggleClass('show-sidebar');
			} else {
				$('body').toggleClass('hide-sidebar');
			}
			$('#wrapper').on('transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd',
				function() {
					if (document.getElementById('productGrid') != null) {
						$('#productGrid').jqxGrid(
						'refresh');
					}
					if (document.getElementById('gridUsuarios') != null) {
						$('#gridUsuarios').jqxGrid(
						'refresh');
					}
			});
	});
}

function setBodySmall() {
	if ($(this).width() < 769) {
		$('body').addClass('page-small');
	} else {
		$('body').removeClass('page-small');
		$('body').removeClass('show-sidebar');
	}
}

//Animate panel function
$.fn['animatePanel'] = function() {

	var element = $(this);
	var effect = $(this).data('effect');
	var delay = $(this).data('delay');
	var child = $(this).data('child');

	// Set default values for attrs
	if (!effect) {
		effect = 'zoomIn';
	}
	if (!delay) {
		delay = 0.06;
	} else {
		delay = delay / 10;
	}
	if (!child) {
		child = '.row > div';
	} else {
		child = '.' + child;
	}

	// Set defaul values for start animation and delay
	var startAnimation = 0;
	var start = Math.abs(delay) + startAnimation;

	// Get all visible element and set opactiy to 0
	var panel = element.find(child);
	panel.addClass('opacity-0');

	// Get all elements and add effect class
	panel = element.find(child);
	panel.addClass('animated-panel').addClass(effect);

	// Add delay for each child elements
	panel.each(function(i, elm) {
		start += delay;
		var rounded = Math.round(start * 10) / 10;
		$(elm).css('animation-delay', rounded + 's');
		// Remove opacity 0 after finish
		$(elm).removeClass('opacity-0');
	});
};