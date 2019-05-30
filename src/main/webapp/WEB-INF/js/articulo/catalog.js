$(document).ready(function() {
	initClassForm();
	initCategoryForm();
	initTypeForm();
	initBrandForm();
})

// Administracion de marca

function initBrandForm() {
	$('#addBrand').click(function() {
		$('#mpBraForm').attr('action', 'create')
		cleanBrandForm();
		setBrandFormForCreate();
		$('#brandModalPopup').modal('show');
	});
	$('#deleteBrand').click(function(){
		$('#mpBraForm').attr('action', 'delete')
		cleanBrandForm();
		setBrandFormForDelete();
		$('#brandModalPopup').modal('show');
	});
	$('#editBrand').click(function(){
		$('#mpBraForm').attr('action', 'edit')
		cleanBrandForm();
		setBrandFormForUpdate();
		$('#brandModalPopup').modal('show');
	});
	$('#mpBraBrandFilter').change(function(e) {
		if ($('#mpBraBrandFilter')[0].selectedIndex > 0) {
			loadEntityData($(this).val(), product.rest.brandByCode, /*[[#{rest.dataload.error}]]*/ '', setBrandData);	
		} else {
			cleanBrandForm();
		}
	});
	$('#mpBraForm').submit(function(e) {
		e.preventDefault();
		var action = $(this).attr('action');
		var actionUrl;
		var doAction = true;
		switch (action) {
		case 'create':
			actionUrl =	product.rest.addBrand;
			break;
		case 'edit':
			actionUrl =	product.rest.updateBrand; 
			break;
		case 'delete':
			var brand = $('#mpBraBrandFilter option:selected').text();
			var swalText = /*[[#{prod.brand.confirmDelete.message}]]*/ '';
			var successText = /*[[#{prod.brand.delete.succes}]]*/ '';
			swal({
					title: /*[[#{control.confirmDelete.title}]]*/ '',
					text: swalText + ' ' + brand,
					type: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#DD6B55',
					confirmButtonText: /*[[#{control.confirmDelete}]]*/ '',
					cancelButtonText: /*[[#{control.cancelDelete}]]*/ ''
				}, 
				function (isConfirm) {
					if (isConfirm) {
						saveEntityData(
							getBrandFormData(), 
							product.rest.deleteBrand,
							successText + ' ' + brand,
							updateBrandCombos
						);
						cleanBrandForm();
					}
				}
			);
		default:
			doAction = false;
			break;
		}
		if (doAction) {
			saveEntityData(
				getBrandFormData(), 
				actionUrl, 
				/*[[#{prod.brand.savedata.success}]]*/ '',
				updateBrandCombos
			);
		}
		$('#brandModalPopup').modal('hide');
	});
}
 
function updateBrandCombos () {
	initCombo('#mpBraBrandFilter', product.rest.getBrands);
	initCombo('#cbMarca', product.rest.getBrands);
}

function setBrandFormForCreate () {
	disableBrandInput(false);
	$('#mpBraBrandFilterLabel').hide();
	$('#mpBraBrandFilter').hide();
	$('#mpBraBrandFilter').prop('disabled', true);
	$('#mpBraInputDivider').hide();
	$('#mpBraTitle').text(/*[[#{prod.brand.addPopupTitle}]]*/ '');
	$('#mpBraSave').text(/*[[#{control.save}]]*/ '');	
}

function setBrandFormForDelete () {
	disableBrandInput(true);
	$('#mpBraBrandFilterLabel').show();
	$('#mpBraBrandFilter').show();
	$('#mpBraBrandFilter').prop('disabled', false);
	$('#mpBraInputDivider').show();
	$('#mpBraTitle').text(/*[[#{prod.brand.deletePopupTitle}]]*/ '');
	$('#mpBraSave').text(/*[[#{control.remove}]]*/ '');
}

function setBrandFormForUpdate () {
	disableBrandInput(false);
	$('#mpBraBrandFilterLabel').show();
	$('#mpBraBrandFilter').show();
	$('#mpBraBrandFilter').prop('disabled', false);
	$('#mpBraInputDivider').show();
	$('#mpBraTitle').text(/*[[#{prod.brand.editPopupTitle}]]*/ '');
	$('#mpBraSave').text(/*[[#{control.save}]]*/ '');	
}

function cleanBrandForm () {
	$('#mpBraName').val('');
	$('#mpBraDesc').val('');
	$('#mpBraBrandFilter').val('');	
}

function disableBrandInput (value) {
	$('#mpBraName').prop('disabled', value);
	$('#mpBraDesc').prop('disabled', value);
}

function getBrandFormData () {
	var data = {
		codigo : $('#mpBraBrandFilter').val(),
		nombre : $('#mpBraName').val(),
		descripcion : $('#mpBraDesc').val()
	};
	return data;
}

function setBrandData (data) {
	$('#mpBraName').val(data.nombre);
	$('#mpBraDesc').val(data.descripcion);
}
 
// Administracion de categoria 

function initCategoryForm() {
	$('#addCategory').click(function() {
		$('#mpCatForm').attr('action', 'create')
		cleanCategoryForm();
		setCategoryFormForCreate();
		$('#categoryModalPopup').modal('show');
	});
	$('#deleteCategory').click(function(){
		$('#mpCatForm').attr('action', 'delete')
		cleanCategoryForm();
		setCategoryFormForDelete();
		$('#categoryModalPopup').modal('show');
	});
	$('#editCategory').click(function(){
		$('#mpCatForm').attr('action', 'edit')
		cleanCategoryForm();
		setCategoryFormForUpdate();
		$('#categoryModalPopup').modal('show');
	});
	$('#mpCatCategoryFilter').change(function(e) {
		if ($('#mpCatCategoryFilter')[0].selectedIndex > 0) {
			loadEntityData($(this).val(), product.rest.categoryByCode, /*[[#{rest.dataload.error}]]*/ '', setCategoryData);	
		} else {
			cleanCategoryForm();
		}
	});
	$('#mpCatForm').submit(function(e) {
		e.preventDefault();
		var action = $(this).attr('action');
		var actionUrl;
		var doAction = true;
		switch (action) {
		case 'create':
			actionUrl =	product.rest.addCategory;
			break;
		case 'edit':
			actionUrl =	product.rest.updateCategory; 
			break;
		case 'delete':
			var category = $('#mpCatCategoryFilter option:selected').text();
			swal({
					title: /*[[#{control.confirmDelete.title}]]*/ '',
					text: /*[[#{prod.category.confirmDelete.message}]]*/ '' + ' ' + category,
					type: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#DD6B55',
					confirmButtonText: /*[[#{control.confirmDelete}]]*/ '',
					cancelButtonText: /*[[#{control.cancelDelete}]]*/ ''
				}, 
				function (isConfirm) {
					if (isConfirm) {
						saveEntityData(
							getCategoryFormData(), 
							product.rest.deleteCategory, 
							/*[[#{prod.category.delete.success}]]*/ '' + ' ' + category,
							updateCategoryCombos
						);
						cleanCategoryForm();
					}
				}
			);
		default:
			doAction = false;
			break;
		}
		if (doAction) {
			saveEntityData(
				getCategoryFormData(), 
				actionUrl,  
				/*[[#{prod.category.savedata.success}]]*/ '',
				updateCategoryCombos
			);
		}
		$('#categoryModalPopup').modal('hide');
	});
}

function updateCategoryCombos () {
	initCombo('#mpCatCategoryFilter', product.rest.getCategories);
	initCombo('#mpClaCatFilter', product.rest.getCategories);
	initCombo('#mpClaCatInput', product.rest.getCategories);
	initCombo('#mpTypCatFilter', product.rest.getCategories);
	initCombo('#mpTypCatInput', product.rest.getCategories);
	initCombo('#cbRubro', product.rest.getCategories);
	cleanCategoryForm();
}

function setCategoryFormForCreate () {
	disableCategoryInput(false);
	$('#mpCatCategoryFilterLabel').hide();
	$('#mpCatCategoryFilter').hide();
	$('#mpCatInputDivider').hide();
	$('#mpCatCategoryFilter').prop('disabled', true);
	$('#mpCatTitle').text(/*[[#{prod.category.addPopupTitle}]]*/ '');
	$('#mpCatSave').text(/*[[#{control.save}]]*/ '');	
}

function setCategoryFormForDelete () {
	disableCategoryInput(true);
	$('#mpCatCategoryFilterLabel').show();
	$('#mpCatCategoryFilter').show();
	$('#mpCatCategoryFilter').prop('disabled', false);
	$('#mpCatInputDivider').show();
	$('#mpCatTitle').text(/*[[#{prod.category.deletePopupTitle}]]*/ '');
	$('#mpCatSave').text(/*[[#{control.remove}]]*/ '');
}

function setCategoryFormForUpdate () {
	disableCategoryInput(false);
	$('#mpCatCategoryFilterLabel').show();
	$('#mpCatCategoryFilter').show();
	$('#mpCatCategoryFilter').prop('disabled', false);
	$('#mpCatInputDivider').show();
	$('#mpCatTitle').text(/*[[#{prod.category.editPopupTitle}]]*/ '');
	$('#mpCatSave').text(/*[[#{control.save}]]*/ '');	
}

function cleanCategoryForm () {
	$('#mpCatName').val('');
	$('#mpCatDesc').val('');
	$('#mpCatCategoryFilter').val('');	
}

function disableCategoryInput (value) {
	$('#mpCatName').prop('disabled', value);
	$('#mpCatDesc').prop('disabled', value);
}

function getCategoryFormData () {
	var data = {
		codigo : $('#mpCatCategoryFilter').val(),
		nombre : $('#mpCatName').val(),
		descripcion : $('#mpCatDesc').val()
	};
	return data;
}

function setCategoryData (data) {
	$('#mpCatName').val(data.nombre);
	$('#mpCatDesc').val(data.descripcion);
}

// Administracion de clase

function initClassForm() {
	$('#addClass').click(function() {
		$('#mpClaForm').attr('action', 'create')
		cleanClassForm();
		setClassFormForCreate();
		$('#classModalPopup').modal('show');
	});
	$('#deleteClass').click(function(){
		$('#mpClaForm').attr('action', 'delete')
		cleanClassForm();
		setClassFormForDelete();
		$('#classModalPopup').modal('show');
	});
	$('#editClass').click(function(){
		$('#mpClaForm').attr('action', 'edit')
		cleanClassForm();
		setClassFormForUpdate();
		$('#classModalPopup').modal('show');
	});
	$('#mpClaCatFilter').change(function(e) {
		relateCombos('#mpClaCatFilter', '#mpClaClassFilter', product.rest.classesByCategory);
	});
	$('#mpClaClassFilter').change(function(e) {
		if ($('#mpClaClassFilter')[0].selectedIndex > 0) {
			loadEntityData($(this).val(), product.rest.classByCode, /*[[#{rest.dataload.error}]]*/ '', setClassData);	
		} else {
			cleanClassForm();
		}
	});
	$('#mpClaForm').submit(function(e) {
		e.preventDefault();
		var action = $(this).attr('action');
		var actionUrl;
		var doAction = true;
		switch (action) {
		case 'create':
			actionUrl =	product.rest.addClass;
			break;
		case 'edit':
			actionUrl =	product.rest.updateClass; 
			break;
		case 'delete':
			var clazz = $('#mpClaClassFilter option:selected').text();
			var swalText =  /*[[#{prod.clazz.confirmDelete.message}]]*/ '';
			var successText = /*[[#{prod.clazz.delete.success}]]*/ '';
			swal({
					title: /*[[#{control.confirmDelete.title}]]*/ '',
					text: swalText + ' ' + clazz,
					type: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#DD6B55',
					confirmButtonText: /*[[#{control.confirmDelete}]]*/ '',
					cancelButtonText: /*[[#{control.cancelDelete}]]*/ ''
				}, 
				function (isConfirm) {
					if (isConfirm) {
						saveEntityData(
							getClassFormData(), 
							product.rest.deleteClass, 
							successText + ' ' + clazz,
							updateClassCombos
						);
						cleanClassForm();
					} 
				}
			);
		default:
			doAction = false;
			break;
		}
		if (doAction) {
			saveEntityData(
				getClassFormData(), 
				actionUrl, 
				/*[[#{prod.clazz.savedata.success}]]*/ '',
				updateClassCombos
			);
		}
		$('#classModalPopup').modal('hide');
	});
}

function updateClassCombos () {
	initCombo('#mpClaClassFilter', product.rest.getClasses);
	initCombo('#mpTypClassFilter', product.rest.getClasses);
	initCombo('#mpTypClassInput', product.rest.getClasses);
	cleanClassForm();
}

function setClassFormForCreate () {
	disableClassInput(false);
	$('#mpClaClassFilterLabel').hide();
	$('#mpClaClassFilter').hide();
	$('#mpClaClassFilter').prop('disabled', true); // Se deshabilita para que no se valide en el formulario
	$('#mpClaCatFilterLabel').hide();
	$('#mpClaCatFilter').hide();
	$('#mpClaInputDivider').hide();
	$('#mpClaCatFilter').prop('disabled', true); // Se deshabilita para que no se valide en el formulario
	$('#mpClaTitle').text(/*[[#{prod.clazz.addPopupTitle}]]*/ '');
	$('#mpClaSave').text(/*[[#{control.save}]]*/ '');	
}

function setClassFormForDelete () {
	disableClassInput(true);
	$('#mpClaClassFilterLabel').show();
	$('#mpClaClassFilter').show();
	$('#mpClaClassFilter').prop('disabled', false);
	$('#mpClaCatFilterLabel').show();
	$('#mpClaCatFilter').show();
	$('#mpClaInputDivider').show();
	$('#mpClaCatFilter').prop('disabled', false);
	$('#mpClaTitle').text(/*[[#{prod.clazz.deletePopupTitle}]]*/ '');
	$('#mpClaSave').text(/*[[#{control.remove}]]*/ '');
}

function setClassFormForUpdate () {
	disableClassInput(false);
	$('#mpClaClassFilterLabel').show();
	$('#mpClaClassFilter').show();
	$('#mpClaClassFilter').prop('disabled', false);
	$('#mpClaCatFilterLabel').show();
	$('#mpClaCatFilter').show();
	$('#mpClaCatFilter').prop('disabled', false);
	$('#mpClaInputDivider').show();
	$('#mpClaTitle').text(/*[[#{prod.clazz.editPopupTitle}]]*/ '');
	$('#mpClaSave').text(/*[[#{control.save}]]*/ '');	
}

function cleanClassForm () {
	$('#mpClaName').val('');
	$('#mpClaDesc').val('');
	$('#mpClaCatFilter').val('');
	$('#mpClaCatInput').val('');
	$('#mpClaClassFilter').val('');	
}

function disableClassInput (value) {
	$('#mpClaName').prop('disabled', value);
	$('#mpClaDesc').prop('disabled', value);
	$('#mpClaCatInput').prop('disabled', value);
}

function getClassFormData () {
	var data = {
		codigo : $('#mpClaClassFilter').val(),
		nombre : $('#mpClaName').val(),
		descripcion : $('#mpClaDesc').val(),
		codRubro :$('#mpClaCatInput').val()
	};
	return data;
}

function setClassData (data) {
	$('#mpClaName').val(data.nombre);
	$('#mpClaDesc').val(data.descripcion);
	$('#mpClaCatInput').val(data.codRubro);
}

// Administracion de tipo

function initTypeForm() {
	$('#addType').click(function() {
		$('#mpTypForm').attr('action', 'create')
		resetTypeForm();
		setTypeFormForCreate();
		$('#typeModalPopup').modal('show');
	});
	$('#deleteType').click(function(){
		$('#mpTypForm').attr('action', 'delete')
		resetTypeForm();
		setTypeFormForDelete();
		$('#typeModalPopup').modal('show');
	});
	$('#editType').click(function(){
		$('#mpTypForm').attr('action', 'edit')
		resetTypeForm();
		setTypeFormForUpdate();
		$('#typeModalPopup').modal('show');
	});
	$('#mpTypCatInput').change(function(e) {
		relateCombos('#mpTypCatInput', '#mpTypClassInput', product.rest.classesByCategory);
	});
	$('#mpTypCatFilter').change(function(e) {
		relateCombos('#mpTypCatFilter', '#mpTypClassFilter', product.rest.classesByCategory);
	});
	$('#mpTypClassFilter').change(function(e) {
		relateCombos('#mpTypClassFilter', '#mpTypTypeFilter', product.rest.typesByClass);
	});
	$('#mpTypTypeFilter').change(function(e) {
		if ($('#mpTypTypeFilter')[0].selectedIndex > 0) {
			loadEntityData($(this).val(), product.rest.typeByCode, /*[[#{rest.dataload.error}]]*/ '', setTypeData);	
		} else {
			cleanTypeFormInput();
		}
	});
	$('#mpTypForm').submit(function(e) {
		e.preventDefault();
		var action = $(this).attr('action');
		var actionUrl;
		var doAction = true;
		switch (action) {
		case 'create':
			actionUrl =	product.rest.addType;
			break;
		case 'edit':
			actionUrl =	product.rest.updateType; 
			break;
		case 'delete':
			var type = $('#mpTypTypeFilter option:selected').text();
			var swalText = /*[[#{prod.type.confirmDelete.message}]]*/ '';
			var successText = /*[[#{prod.type.delete.success}]]*/ '';
			swal({
					title: /*[[#{control.confirmDelete.title}]]*/ '',
					text: swalText + ' ' + type,
					type: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#DD6B55',
					confirmButtonText: /*[[#{control.confirmDelete}]]*/ '',
					cancelButtonText: /*[[#{control.cancelDelete}]]*/ ''
				}, 
				function (isConfirm) {
					if (isConfirm) {
						saveEntityData(
							getTypeFormData(), 
							product.rest.deleteType, 
							successText + ' ' + type,
							updateTypeCombos
						);
						resetTypeForm();
					} 
				}
			);
		default:
			doAction = false;
			break;
		}
		if (doAction) {
			saveEntityData(
				getTypeFormData(), 
				actionUrl,  
				/*[[#{prod.type.savedata.success}]]*/ '',
				updateTypeCombos
			);
		}
		$('#typeModalPopup').modal('hide');
	});
}

function updateTypeCombos () {
	initCombo('#mpTypTypeFilter', product.rest.getTypes);
}

function setTypeFormForCreate () {
	disableTypeInput(false);
	$('#mpTypCatFilterLabel').hide();
	$('#mpTypCatFilter').hide();
	$('#mpTypCatFilter').prop('disabled', true); // Se deshabilita para que no se valide en el formulario
	$('#mpTypClassFilterLabel').hide();
	$('#mpTypClassFilter').hide();
	$('#mpTypClassFilter').prop('disabled', true); // Se deshabilita para que no se valide en el formulario
	$('#mpTypTypeFilterLabel').hide();
	$('#mpTypTypeFilter').hide();
	$('#mpTypTypeFilter').prop('disabled', true); // Se deshabilita para que no se valide en el formulario
	$('#mpTypInputDivider').hide();
	$('#mpTypTitle').text(/*[[#{prod.type.addPopupTitle}]]*/ '');
	$('#mpTypSave').text(/*[[#{control.save}]]*/ '');	
}

function setTypeFormForDelete () {
	disableTypeInput(true);
	$('#mpTypCatFilterLabel').show();
	$('#mpTypCatFilter').show();
	$('#mpTypCatFilter').prop('disabled', false);				
	$('#mpTypClassFilterLabel').show();
	$('#mpTypClassFilter').show();
	$('#mpTypClassFilter').prop('disabled', false);
	$('#mpTypTypeFilterLabel').show();
	$('#mpTypTypeFilter').show();
	$('#mpTypTypeFilter').prop('disabled', false);
	$('#mpTypInputDivider').show();
	$('#mpTypTitle').text(/*[[#{prod.type.deletePopupTitle}]]*/ '');
	$('#mpTypSave').text(/*[[#{control.remove}]]*/ '');
}

function setTypeFormForUpdate () {
	disableTypeInput(false);
	$('#mpTypCatFilterLabel').show();
	$('#mpTypCatFilter').show();
	$('#mpTypCatFilter').prop('disabled', false);				
	$('#mpTypClassFilterLabel').show();
	$('#mpTypClassFilter').show();
	$('#mpTypClassFilter').prop('disabled', false);
	$('#mpTypTypeFilterLabel').show();
	$('#mpTypTypeFilter').show();
	$('#mpTypTypeFilter').prop('disabled', false);
	$('#mpTypInputDivider').show();
	$('#mpTypTitle').text(/*[[#{prod.type.editPopupTitle}]]*/ '');
	$('#mpTypSave').text(/*[[#{control.save}]]*/ '');	
}

function resetTypeForm () {
	$('#mpTypName').val('');
	$('#mpTypDesc').val('');
	$('#mpTypCatFilter').val('');
	$('#mpTypCatInput').val('');	
	emptyCombo('#mpTypClassFilter', true);
	emptyCombo('#mpTypClassInput', true);
	emptyCombo('#mpTypTypeFilter', true);
}

function cleanTypeFormInput () {
	$('#mpTypName').val('');
	$('#mpTypDesc').val('');
	$('#mpTypCatInput').val('');
	$('#mpTypClassInput').val('');
}

function disableTypeInput (value) {
	$('#mpTypName').prop('disabled', value);
	$('#mpTypDesc').prop('disabled', value);
	$('#mpTypCatInput').prop('disabled', value);
	$('#mpTypClassInput').prop('disabled', value);
}

function getTypeFormData () {
	var data = {
		codigo : $('#mpTypTypeFilter').val(),
		nombre : $('#mpTypName').val(),
		descripcion : $('#mpTypDesc').val(),
		codClase :$('#mpTypClassInput').val()
	};
	return data;
}

function setTypeData (data) {
	$('#mpTypName').val(data.nombre);
	$('#mpTypDesc').val(data.descripcion);
	$('#mpTypCatInput').val(data.codRubro);
	relateCombos(
			'#mpTypCatInput', 
			'#mpTypClassInput', 
			product.rest.classesByCategory, 
			data.codClase
	);
}