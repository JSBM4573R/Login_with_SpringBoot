/**
 * Funcion para autenticar un usuario con la api
 */
function login() {
        //con .val() puedo capturar los datos contenidos en los input mediante su id
        let email = $("#user_email").val();
        let password = $("#user_password").val();

    //Utilizo la funcion de Jquery $.ajax para hacer un llamado de forma asincronica 
    //a un servicio web.
    $.ajax({
        //Estructura Ajax:
        //1. Url del servicio
        url: `http://localhost:8080/api/user/${email}/${password}`,
        //2. tipo de peticion
        type: 'GET', //consulta
        //3. tipo de contenido
        dataType: 'json',
        //4. envio datos capturados por el usuario a la peticion
        //(En este caso como realizo peticion GET de consulta no envio ningun dato)
        //5. crear una funcion success: para saber si todo sale ok
        success:function (respuesta) {
            console.log(respuesta)
            resultado(respuesta)
        },
        //6. crear una funcion error: para saber si ocurre un error
        error:function (xhr, status) {
            console.log(status)
            alert("Porfavor digita usuario y contraseña")
        },
        complete:function (xhr, status) {
            console.log(status)
            console.log("todo melo")
        }
    });
}

/**
 * Funcion para registrarse en una api
 */
function registrar() {
    let nombre = $("#user_name_register").val()
    let correo = $("#user_email_register").val()
    let clave = $("#user_password_register").val()
    let claveConfirmada = $("#user_confirmPassword_register").val()
    //creamos una variable local que contiene los datos en un objeto javascript
    //se usa .val() para capturar los datos contenidos en los input
    var datos={
        //se usan las mismas variables de los atributos declarados en la clase
        //se envian en el mismo orden en el que fueron estruturados en la clase
        email:$("#user_email_register").val(),
        password:$("#user_password_register").val(),
        confirmPassword:$("#user_confirmPassword_register").val(),
        name:$("#user_name_register").val(),
    }
    if (nombre.length>0 && correo.length>0 && clave.length>0 && claveConfirmada.length>0) {
        if (clave===claveConfirmada) {
            let datosPeticion=JSON.stringify(datos);
            //Llamo la funcion Ajax de JQuery
            $.ajax({
                //url de la api a la cual realizare la peticion,
                url:"http://localhost:8080/api/user/new",
                //Tipo de peticion a realziar GET, PUT, POST o DELETE,
                type: "post",
                //El tipo de dato que voy a enviar
                dataType: "json",
                //El tipo de contenido que voy a enviar
                contentType: "application/JSON; charset=utf-8",
                //Los datos que voy a enviar capturados y convertidos en JSON
                data: datosPeticion,
                
                //funcion success
                success:function (respuesta) {
                    console.log(respuesta)
                    alert("Registrado Exitosamente!")
                    window.location.reload()
                },
                //funcion error
                error: function (xhr, status) {
                    console.log(status)
                }
            });
        }else{
            alert("Tu contraseña no coincide")
            $("#user_confirmPassword_register").val("").focus()
        }
    }else{
        alert("Porfavor llena los campos obligatorios")
        //$("#user_name_register").val("").focus()
    }
    
}

/**
 * 
 * @param {*} respuesta 
 * usado para gestionar la respuesta que se recibe de la peticion
 * se obtiene de la respuesta el id y el nombre para mostrar en un mensaje
 * asi mismo para realizar validaciones de campos vacios o usuario no registrado en la bd
 * se usa funcion .html() para convertir el mensaje y poder mostrar en formato html
 */
function resultado(respuesta) {
    let id = respuesta.id
    let nombre = respuesta.name
    clave = $("#user_password").val()

    if (id!=null && clave.length>0) {
        $("#mensaje").html("Bienvenido(a) "+nombre)
        $("#btn_autenticar").hide()
        $("#modalLogin").modal("hide")
    }else{
        alert("Usuario y/o contraseña incorrectos")
    $("#user_password").val("").focus()
    }
}

/**
 * estadoInicial Focusea el puntero del mouse en el input del email
 * ademas oculta el mensaje que se carga en la pagina inicial
 */
function estadoInicial() {
    $("#user_email").focus();
    $("#mensaje").hide();
}

