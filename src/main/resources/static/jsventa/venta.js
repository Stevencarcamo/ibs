let clientes = {
    id: 0,
    nombre: ""
};

let productos = {
    id: 0,
    nombre: ""
};

/* funcion para cargar los elementos llamados */
$(document).ready(function () {
    resetDetalles();
    cargarClientes();
    cargarProductos();
    $("#guardarVenta").click(guardar);

    $("#agregarDetalle").click(agregar);

    $("body").on('click', '.agregarCliente', function () {

        agregarCliente($(this).parent().parent().children('td:eq(0)').text(), $(this)
            .parent()
            .parent().children('td:eq(1)').text());
    });

    $("body").on('click', '.agregarProducto', function () {

        agregarProducto($(this).parent().parent().children('td:eq(0)').text(), $(this)
            .parent()
            .parent().children('td:eq(2)').text());
    });

});
/* Boton de agregar cliente en la tabla */

function agregarCliente(id, nombre) {
    clientes.id = id;
    clientes.nombre = nombre;

    $("#cliente").val(clientes.nombre);
}

function agregarProducto(id, nombre) {
    productos.id = id;
    productos.nombre = nombre;

    $("#producto").val(productos.nombre);
}

function cargarClientes() {
    $("#tablaClientes").DataTable({
        "ajax": {
            "url": "/venta/getClientes",
            "method": "Get"
        },
        "columns": [{
                "data": "id",
                "width": "5%"
            },
            {
                "data": "nombre",
                "width": "10%"
            },
            {
                "data": "apellido",
                "width": "10%"
            },
            {
                "data": "direccion",
                "width": "30%"
            },
            {
                "data": "ncr",
                "width": "10%"
            },
            {
                "data": "tipo",
                "width": "10%"
            },
            {
                "data": "giro",
                "width": "10%"
            },
            {
                "data": "telefono",
                "width": "10%"
            },
            {
                "data": "operacion",
                "width": "5%"
            }
        ],
        "scrollY": 200,
        "language": {
            "lengthMenu": "Mostrar _MENU_ ",
            "zeroRecords": "Datos no encontrados",
            "info": "Mostar páginas _PAGE_ de _PAGES_",
            "infoEmpty": "Datos no encontrados",
            "infoFiltered": "(Filtrados por _MAX_ total registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "Anterior",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
}

function cargarProductos() {
    $("#tProductos").DataTable({
        "ajax": {
            "url": "/venta/getProductos",
            "method": "Get"
        },
        "columns": [{
                "data": "id",
                "width": "5%"
            },
            {
                "data": "codigo",
                "width": "15%"
            },
            {
                "data": "nombreProducto",
                "width": "15%"
            },
            {
                "data": "presentacion",
                "width": "15%"
            },
            {
                "data": "existencias",
                "width": "15%"
            },
            {
                "data": "categoria",
                "width": "15%"
            },
            {
                "data": "marca",
                "width": "15%"
            },
            {
                "data": "operacion",
                "width": "5%"
            }

        ],
        "scrollY": 200,
        "language": {
            "lengthMenu": "Mostrar _MENU_ ",
            "zeroRecords": "Datos no encontrados",
            "info": "Mostar páginas _PAGE_ de _PAGES_",
            "infoEmpty": "Datos no encontrados",
            "infoFiltered": "(Filtrados por _MAX_ total registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "Anterior",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
}

function agregar() {
    var desc = $("#descuento").val() / 100;

    $.ajax({
        url: "/venta/agregarDetalle",
        method: "Get",
        data: {
            cantidad: $("#cantidad").val(),
            descuento: desc,
            idProducto: productos.id
        },
        success: function (response) {

            alert("Detalle agregado");
            $("#cantidad").val(0);
            $("#descuento").val(0);
            $("#producto").val("");
            productos.id = 0;
            productos.nombre = "";
            cargarDetalles();
        },
        error: function (response) {
            alert("NO SE AGREGO EL DETALLE");
        }
    });
}

function cargarDetalles() {
    $.ajax({
        url: "/venta/allDetalles",
        method: "Get",
        success: function (response) {
            $("#tDetalles").html("");
            response.forEach(i => {
                var st = (i.productos.precioVenta - (i.productos.precioVenta * i
                    .descuento)) * i.cantidad;
                $("#tDetalles").append("" +
                    "<tr>" +
                    "<td>" + i.cantidad + "</td>" +
                    "<td>" + i.productos.nombreProducto + "</td>" +
                    "<td>$" + i.productos.precioVenta + "</td>" +
                    "<td>" + (i.descuento * 100) + "%</td>" +
                    "<td>$" + st + "</td>" +
                    "<td><button class='btn btn-danger'>eliminar</button></td>" +
                    "</tr>" +
                    "");
            });
        },
        error: function (response) {}
    });
}

function resetDetalles() {
    $.ajax({
        url: "/venta/resetDetalles",
        method: "Get"
    });
}

function guardar() {
    var nf = $("#numeroFactura").val();
    var tv = $("#tipoVenta").val();
    console.log(nf)
    $.ajax({
        url: "/venta/save",
        method: "Get",
        data: {
            numeroFactura: nf,
            tipoVenta: tv,
            idCliente: clientes.id
        },
        success: function (response) {

            alert("DETALLE GUARDADO CORRECTAMENTE...");
            location.reload(); //para recargar la página
        },
        error: function (response) {
            alert("DETALLE NO GUARDADO ...");
        }
    })
}