(function( $ ) {
    var fieldsConfig = new Map();  
	var triggersConfig = new Map();
	var submitConfig = new Map();
	var currentMode;
    
    $.fn.configField = function(_mode, fieldId, _state) {
    	var field = getFormInput(this, fieldId);
    	if (field[0]) {
    		if (!fieldsConfig.get(this[0])) {
    			fieldsConfig.set(this[0], new Map());
    		}
    		var fieldConfig = {field : field[0], mode : _mode, state : _state};
    		fieldsConfig.get(this[0]).set(field[0], fieldConfig)
			console.log('Field config [' + field[0].id + ']');
    	} else {
			console.error('Field missmatched [' + fieldId + ']');
    	}
        return this;
    };
  
    $.fn.configTrigger = function(_mode, elementId) {
		var element = getElement(elementId);
    	if (element[0]) {
			var formMode = {form:this[0], mode:_mode}
			triggersConfig.set(element[0], formMode);
			console.log('Trigger config [' + elementId + '] for mode [' + _mode + ']');
			element.click(function() {
				var _formMode = triggersConfig.get(this);
				console.log('Trigger clicked for form [' + _formMode.form.id + '] on mode [' + _formMode.mode + ']');
				resetForm(_formMode);
				initForm(_formMode);
			});
    	} else {
			console.error('Element missmatched [' + elementId + ']');
    	}
        return this;
    };
    
    $.fn.configSubmit = function(_mode, _action) {
    	submitConfig.set(_mode, _action);
		this.submit(function(e) {
			e.preventDefault();
			action = submitConfig.get(currentMode);
			console.log('Form submitted in mode [' + currentMode + '] with action [' + action + ']');
			var formData = getFormData(this);
			saveEntityData(formData, action, 'Todo OK');
		});
        return this;
    };
    
    $.fn.fillCombo = function(elementId, restUrl, callback) {
		console.log('El combo [' + elementId + '] se llena con el servicio rest [' + restUrl + ']');
		var optDefault = $('#' + elementId + ' option:first-child');
		var element = $('#' + elementId);
    	$.ajax({
    		type : 'POST',
    		url : restUrl,
    		dataType: 'json',
    		contentType: 'application/json',
    		success : function(options) {
    			element.empty();
    			if (optDefault[0]) {
    				element.append(optDefault[0]);
    			}
    			$.each(options, function(index, option) {
    				element.append(new Option(option.nombre, option.codigo));
    			});
    		},
    		error: function () {
    			if (callback) {
    				callback();
    			}
    		}
    	});
        return this;
    };

    var combosChains = new Map();
    
	$.fn.chainCombo = function(_srcCombo, _chainedCombo, _restUrl) {
		console.log('El combo [' + _chainedCombo + '] se llena con el servicio rest [' + _restUrl + '] tomando como parametro de filtro el valor seleccionado del combo [' + _srcCombo + ']');
		var srcCombo = $('#' + _srcCombo);
		if (!combosChains.get(srcCombo[0])) {
			combosChains.set(srcCombo[0], []);
		}
		var chainConfig = {srcCombo:_srcCombo, chainedCombo:_chainedCombo, restUrl:_restUrl};
		combosChains.get(srcCombo[0]).push(chainConfig);
		srcCombo.change(function () {
			var chainConfigs = combosChains.get(this);
			$.each(chainConfigs, function(index, value){
				doComboChaining(value);
			});
		});
        return this;
    };
    
	$.fn.defaultMode = function defaultMode (mode) {
		currentMode = mode;
		return this;
	};
	
	function doComboChaining(chainConfig) {
		var code = $('#' + chainConfig.srcCombo + ' option:selected').attr('value');
		var chainedCombo = $('#' + chainConfig.chainedCombo);
		var optDefault = $('#' + chainConfig.chainedCombo + ' option:first-child');
		if (!code) {
			chainedCombo.empty();
			if (optDefault[0]) {
				chainedCombo.append(optDefault);
			}
			return;
		}
		$.ajax({
			type : 'POST',
			url : chainConfig.restUrl,
			dataType: 'json',
			contentType: 'application/json',
			data : code,
			success : function(options) {
				chainedCombo.empty();
				if (optDefault[0]) {
					chainedCombo.append(optDefault);
				}
				$.each(options, function(index, option) {
					chainedCombo.append(new Option(option.nombre, option.codigo));
				});
				/*
				if (selectedValue) {
					$chainedCombo.val(selectedValue);
				}
				*/
			},
			error: function () {
				/*
				if (callback) {
					callback();
				}
				*/
			}
		});
	}
	
	function getFormData (form) {
		console.log('Obteniendo la data del formulario');
		var result = {};
		$.each($(form).serializeArray(), function(){
			result[this.name] = this.value;	
		});
		return result;
	}
	
	function resetForm(formMode) {
		console.log('Reseteando el formulario');
		$(formMode.form).find(':input').show().prop('disabled', false);
	}
	
	function initForm(formMode) {
		console.log('Inicializando el formulario');
		var fieldsMap = fieldsConfig.get(formMode.form);
		currentMode = formMode.mode;
		fieldsMap.forEach(function(value, key){
			if (formMode.mode == value.mode) {
				console.log('En modo [' + value.mode + '] el campo [' + value.field.id + '] esta en estado [' + value.state + ']');
				var field = value.field;
				switch (value.state) {
					case 'disabled':
						$(field).prop('disabled', true);
					break;
					case 'hidden':
						$(field).hide();
					break;
				}
			}
		});
	}

	function getFormInput(form, fieldId) {
		return form.find('input[id="' + fieldId + '"]')
	}

	function getElement(elementId) {
		return $("#" + elementId);
	}
}(jQuery));