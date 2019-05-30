var formMode;
var currentrow = -1;
$(document).ready(function() {
    // prepare the data
    var source = {
        datatype : 'json',
        id : 'id',
        url : usuario.rest.findAll,
        dataFields: [
                { name: 'alias', type: 'string' },
                { name: 'nombre', type: 'string' },
                { name: 'apellido', type: 'string' },
                { name: 'email', type: 'string' }
         ]
    };
    
    var dataAdapter = new $.jqx.dataAdapter(source);
    // initializa la grilla de usuarios
    $('#gridUsuarios').jqxGrid({
        width : '100%',
        theme : 'energyblueproeza',
        source : dataAdapter,
        pageable : false,
        autoheight : false,
        altrows : true,
        sortable : true,
		enablehover : false,
        enabletooltips : true,
        columns : [ {
            text : /*[[#{user.grid.alias}]]*/ 'Alias',
            dataField : 'alias',
            width : '20%'
        }, {
            text : /*[[#{user.grid.name}]]*/ 'Nombre',
            dataField : 'nombre',
            width : '25%'
        }, {
            text : /*[[#{user.grid.surname}]]*/ 'Apellido',
            dataField : 'apellido',
            width : '25%'
        }, {
            text : /*[[#{user.grid.email}]]*/ 'E-mail',
            dataField : 'email',
            width : '30%'
        } ]
    });
    // create context menu
    var contextMenu = $('#gridMenu').jqxMenu({
        width : 180,
        autoOpenPopup : false,
        mode : 'popup'

    });
    $('#gridUsuarios').on('contextmenu', function() {
        return false;
    });
    $('#newUser').click(function(){
        initCreationForm();
    });
    // handle context menu clicks.
    $('#gridMenu').on('itemclick', function(event) {
        var args = event.args;
        currentrow = $('#gridUsuarios').jqxGrid('getselectedrowindex');// open the popup window when the user clicks a button.
        var offset = $('#gridUsuarios').offset();
        // get the clicked row's data and initialize the input fields.
        if ($.trim($(args).text()) == /*[[#{user.admin.menu.edit}]]*/ 'Editar usuario') {
            initEditionForm();
        } else if ($.trim($(args).text()) == /*[[#{user.admin.menu.remove}]]*/ 'Eliminar usuario') {
            deleteUser();
        } else if ($.trim($(args).text()) == /*[[#{user.admin.menu.create}]]*/ 'Nuevo usuario') {
            initCreationForm();
        } else if ($.trim($(args).text()) == /*[[#{user.admin.menu.setPhoto}]]*/ 'Establecer foto') {
            openBrowseImageDialog();
        }
    });
    $('#gridUsuarios').on(
        'rowclick',
        function(event) {    
            if (event.args.rightclick) {    
                $('#gridUsuarios').jqxGrid('selectrow', event.args.rowindex);
                var scrollTop = $(window).scrollTop();
                var scrollLeft = $(window).scrollLeft();
                contextMenu.jqxMenu(
                    'open', 
                    parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft,
                    parseInt(event.args.originalEvent.clientY) + 5 + scrollTop
                );
                return false;
            }
        }
    );
    
    $('#userForm').submit(function( e ) {
           e.preventDefault();
           var formData = {};
           // Reuno la data del formulario y elimino los "undefined" 
           $.each(this, function(i, v) {
               var input = $(v);
               var itemId = input.attr('id');
                  formData[itemId] = input.val();
               delete formData['undefined'];
           });
           switch (formMode) {
        case 'creation' :
            addUser(formData);
            break;
        case 'edition' :
                   updateUser(formData);
            break;
        default:
            break;
        }
        return false; 
    });
    
    // Hide popup when cancel
    $('#cancel').click(function() {
        hideForm();
    });

    // Configuracion del control de ingreso del precio
    $('#precio').inputmask('currency', {
        'autoUnmask' : true
    });
    $('#costo').inputmask('currency', {
        'autoUnmask' : true
    });
    $('#eraser').click(function () {
        $('#gridUsuarios').jqxGrid('clear');
    });
    $('#btnGenerate').pGenerator({
        'bind': 'click',
        'passwordElement': null,
        'displayElement': $('#password'),
        'passwordLength': 8,
        'uppercase': true,
        'lowercase': true,
        'numbers':   true,
        'specialChars': false,
        'onPasswordGenerated': function (pwd) {} 
    });
});

function showForm () {
	$('#commandBar').hide();
    $('#editionPanel').show();
	$('#gridPanel').hide();	
}

function hideForm () {
    $('#editionPanel').hide();
    $('#commandBar').show();
    $('#gridPanel').show();    
}

function openBrowseImageDialog() {
    var dataRecord = $('#gridUsuarios').jqxGrid('getrowdata', currentrow);
    var fileInputOptions = {
        language: /*[[#{app.language}]]*/ 'es',
        showUpload : true,
        showRemove : true,
        uploadAsync: true,
        maxFileCount : 1,
        allowedFileExtensions : ['jpg', 'png'],
        allowedFileTypes : ['image'],
        uploadUrl: usuario.rest.setPhoto + '?alias=' + dataRecord.alias
    };
     // File input plugin options
    $('#uploadImage').fileinput(fileInputOptions);
    $('#addImagePopup').modal('show');
}

function initCreationForm () {
    formMode = 'creation';
    $('#alias').prop('disabled', false);
    $('#alias').val('');
    $('#nombre').val('');
    $('#apellido').val('');
    $('#password').val('');
    $('#btnGenerate').trigger('click');
    $('#email').val('');
    $('#formTitle').text(/*[[#{user.admin.creation.title}]]*/ 'Creacion de usuario');
    $('#formFooter').text(/*[[#{user.admin.creating}]]*/ 'Creando nuevo usuario');
    $('#roles').val([]);
    showForm();
}

function initEditionForm () {
    var dataRecord = $('#gridUsuarios').jqxGrid('getrowdata', currentrow);
    formMode = 'edition';
    findUser(dataRecord.alias);
    $('#alias').prop('disabled', true);
    $('#alias').val(dataRecord.alias);
    $('#nombre').val(dataRecord.nombre);
    $('#apellido').val(dataRecord.apellido);
    $('#email').val(dataRecord.email);
    $('#password').val('');
    $('#formTitle').text(/*[[#{user.admin.edition.title}]]*/ 'Edicion de usuario');
    var footerText = /*[[#{user.admin.editing}]]*/ 'Editando el usuario: ';
    $('#formFooter').text(footerText + dataRecord.nombre + ' ' + dataRecord.apellido);
    showForm();
}

function addUser (formData) {
    $.ajax({
        contentType : 'application/json; charset=utf-8',
        type : 'POST',
        url : usuario.rest.create,
        dataType: 'json',
        data : JSON.stringify(formData),
        success : function() {
            $.notify({
                title: /*[[#{user.create.success.title}]]*/ 'Creacion exitosa',
                message: /*[[#{user.create.success.message}]]*/ 'Se creo el usuario correctamente'
            },{
                type: 'success'
            });
            $('#gridUsuarios').jqxGrid('addrow', null, getRowData(), 0);
        },
        error: function () {            
            $.notify({
                title: /*[[#{user.create.error.title}]]*/ 'Creación errónea',
                message: /*[[#{user.create.error.message}]]*/ 'Ocurrió un error en la creacion del usuario'
            },{
                type: 'danger'
            });
        }
    });
    hideForm();
}

function updateUser (formData) {
    $.ajax({
        type : 'POST',
        url : usuario.rest.update,
        dataType: 'json',
        contentType: 'application/json',
        data : JSON.stringify(formData),
        success : function() {
            $.notify({
                title: /*[[#{user.update.success.title}]]*/ '' ,
                message: /*[[#{user.update.success.message}]]*/ ''
            },{
                type: 'success'
            });
            if (currentrow >= 0) {
                   var rowid = $('#gridUsuarios').jqxGrid('getrowid', currentrow);
                      $('#gridUsuarios').jqxGrid('updaterow', rowid, getRowData());
               }
        },
        error: function () {
            $.notify({
                title: /*[[#{user.update.error.title}]]*/ '',
                message: /*[[#{user.update.error.message}]]*/ ''
            },{
                type: 'danger'
            });
        }
    });
    hideForm();
}

function getRowData () {
    return {
        alias : $('#alias').val(),
        nombre : $('#nombre').val(),
        apellido : $('#apellido').val(),
        email : $('#email').val()
    };
}

function deleteUser () {
    var dataRecord = $('#gridUsuarios').jqxGrid('getrowdata', currentrow);
    $.ajax({
        type : 'POST',
        url : usuario.rest.remove + '/' + dataRecord.alias,
        dataType: 'json',
        contentType: 'application/json',
        success : function() {
            $.notify({
                title: /*[[#{user.remove.success.title}]]*/ '',
                message: /*[[#{user.remove.success.message}]]*/ ''
            },{
                type: 'success'
            });
            if (currentrow >= 0) {
                   var rowid = $('#gridUsuarios').jqxGrid('getrowid', currentrow);
                   $('#gridUsuarios').jqxGrid('deleterow', rowid);
            }
        },
        error: function () {
            $.notify({
                title: /*[[#{user.remove.error.title}]]*/ '',
                message: /*[[#{user.remove.error.message}]]*/ ''
            },{
                type: 'danger'
            });
        }
    });
}

function findUser (alias) {
    $.ajax({
        url : usuario.rest.find + '/' + alias,
        type : 'POST',
        dataType: 'json',
        contentType : 'application/json',
        success : function (data, status, jqXHR) {
            $('#roles').val(data.roles);
        },
        error: function (jqXHR, status) {
            $.notify({
                title: /*[[#{user.find.error.title}]]*/ '',
                message: /*[[#{user.find.error.message}]]*/ '' + alias
            },{
                type: 'danger'
            });
        }
    });
}