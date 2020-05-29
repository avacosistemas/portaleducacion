import { Injector } from '@angular/core';
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: true,
    hmr       : false,
    bypassSec: true, // pasar a true para evitar ws
    dummyServices: true, // pasar a true para evitar ws
    
    /** URLs of Services **/
    
    /** AUTHENTICATION */
    AUTHENTICATION_URL: 'http://localhost:8080/ws-rest-authentication/auth',
    USER_DETAIL_URL: 'http://localhost:8080/ws-rest-authentication/user',
    AUTHENTICATION_REFRESH_TOKEN_URL: 'http://localhost:8080/ws-rest-authentication/refresh',
    PASSWORD_RESET_URL: 'http://localhost:8080/ws-rest-authentication/password/reset/',
    PASSWORD_UPDATE_URL: 'http://localhost:8080/ws-rest-authentication/password/update/',

    /** USER */
    USER_CRUD_URL: 'http://localhost:8080/ws-rest-authentication/users/',
    USER_VALIDATION_ADD_URL : undefined,
    // USER_VALIDATION_ADD_URL : 'http://localhost:8080/ws-rest-authentication/users/save/validation/',
    USER_VALIDATION_EDIT_URL : 'http://localhost:8080/ws-rest-authentication/users/update/validation/',
    
    /** PARAMETER */
    PARAMETER_CRUD_URL: 'http://localhost:8080/ws-rest-parameter/parameters/',
    PARAMETER_VALIDATION_ADD_URL : 'http://localhost:8080/ws-rest-authentication/parameters/save/validation/',
    PARAMETER_VALIDATION_EDIT_URL : 'http://localhost:8080/ws-rest-authentication/parameters/update/validation/',

    /** PROFILE */
    PROFILE_CRUD_URL: 'http://localhost:8080/ws-rest-authentication/profiles/',

    /** ROLE */
    ROLE_CRUD_URL: 'http://localhost:8080/ws-rest-authentication/roles/',

    /** I18n */
    I18N_CRUD_URL: 'http://localhost:8080/ws-rest-parameter/i18ns',

    /** FAQ */
    FAQ_CRUD_URL: 'http://localhost:8080/ws-rest-parameter/faqs/',
    // FAQ Portal
    FAQ_GET_ALL_URL : 'http://localhost:8080/ws-rest-parameter/portal/faqs',
    /** PERMISSION */
    PERMISSION_CRUD_URL: 'http://localhost:8080/ws-rest-authentication/permissions/',
    
    /* CONTACT US */
    CONTACT_US_CRUD : 'http://localhost:8080/ws-rest-parameter/contact-us/',
    CONTACT_US_SEND : 'http://localhost:8080/ws-rest-parameter/contact-us/send/',

    /** Perfil Pregunta*/
    PERFIL_PREGUNTA_CRUD_URL: 'http://localhost:8080/ws-rest-inversor/preguntaPerfil/abm/',

    CLIENTE_CRUD_URL: 'http://localhost:8080/ws-rest-cliente/clientes/',
    REGISTRO_CLIENTE_CREAR_PERSONA_DATOS_PERSONALES: 'http://localhost:8080/ws-rest-registro/registro/persona/',
    REGISTRO_CLIENTE_CREAR_EMPRESA_DATOS_PERSONALES: 'http://localhost:8080/ws-rest-registro/registro/empresa/',	
    
    REGISTRO_CLIENTE_CRUD_URL: 'http://localhost:8080/ws-rest-registro/registro/clientes/',
    REGISTRO_CLIENTE_PERSONA_DATOS_PERSONALES_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/datosPersona/validar/',
    REGISTRO_CLIENTE_EMPRESA_DATOS_PERSONALES_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/datosEmpresa/validar/',
    REGISTRO_CLIENTE_CONTACTO_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/contacto/validar/',
    REGISTRO_CLIENTE_CUENTA_BANCARIA_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/cuentaBancaria/validar/',
    REGISTRO_CLIENTE_INGRESOS_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/ingreso/validar/',
    
    REGISTRO_CLIENTE_DATOS_PERSONALES_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/datosPersonales/validar/',
    
    REGISTRO_CLIENTE_INGRESOS_PERSONA_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/ingresoPersona/validar/',
    REGISTRO_CLIENTE_INGRESOS_EMPRESA_VALIDATION: 'http://localhost:8080/ws-rest-registro/registro/ingresoEmpresa/validar/',

    /** Entidad Bancaria */
    ENTITDAD_BANCARIA_CRUD: 'http://localhost:8080/ws-rest-registro/entidadBancaria/',

    /* CLIENT DETAIL */
    CLIENTE_DATOS_PERSONALES_VALIDATION: 'http://localhost:8080/ws-rest-cliente/clientes/datosPersonales/validar/',
    CLIENTE_CUENTA_BANCARIA_VALIDATION: 'http://localhost:8080/ws-rest-cliente/clientes/cuentaBancaria/validar/',
    CLIENTE_CONTACTO_VALIDATION: 'http://localhost:8080/ws-rest-cliente/clientes/contacto/validar/',
    CLIENTE_PERSONA_INGRESOS_VALIDATION: 'http://localhost:8080/ws-rest-cliente/clientes/ingresoPersona/validar/',
    CLIENTE_EMPRESA_INGRESOS_VALIDATION: 'http://localhost:8080/ws-rest-cliente/clientes/ingresoEmpresa/validar/',
    
    /* ADMIN CLIENTE */
    SOLICITUD_PRESTAMO_CRUD_URL: 'http://localhost:8080/ws-rest-prestamo/solicitudprestamo/',
    SUBASTA_PRESTAMO_CRUD_URL: 'http://localhost:8080/ws-rest-prestamo/subastaprestamo/',
    PRESTAMO_CRUD_URL: 'http://localhost:8080/ws-rest-prestamo/prestamo/',
    SCORING_SOLICITANTE_CRUD_URL: 'http://localhost:8080/ws-rest-prestamo/perfilclienteprestamo/abm/',
    LIMITES_INVERSIONISTA_CRUD_URL: 'http://localhost:8080/ws-rest-inversor/perfilinversormontos/abm/',
    ADMIN_ACREDITACION_WS_CRUD_URL: 'http://localhost:8080/ws-rest-acreditacion/acreditacion/',

    /* Config General URl*/
    URL_LOGIN: 'auth/login',
    URL_PASSWORD_UPDATE: 'password/update',
    URL_FORGOT_PASSWORD: 'auth/forgot-password',
    URL_DASHBOARD: '/',
    URL_PARAMETER: 'parameter',
    URL_PERMISSION: 'permission',
    URL_ROLE: 'role',
    URL_PROFILE: 'profile',
    URL_USER: 'user',
    URL_FAQ: 'faq',
    URL_CONTACT_US: 'contact-us',
    URL_PORTAL_CONTACT_US: 'portal/contact-us',
    URL_PORTAL_FAQ: 'portal/faq',

    
    // Business
    URL_PREGUNTA_PERFIL: 'preguntaPerfil',
    URL_LIMITES_INVERSIONISTA: 'limitesInversionista',
    URL_SCORING_SOLICITANTE: 'scoringSolicitante',
    URL_LISTADO_CLIENTE: 'clienteListado',
    URL_CLIENTE_DETAIL: 'client/detail/:id',
    CLIENT_DETAIL_URL: 'clientform/detail/',
    URL_REGISTRO_CLIENTE: 'registro/cliente',
    URL_ACREDITACION: 'acreditacion',
    URL_LOAN_CLIENT_REQUEST: 'solicitudPrestamo',
    URL_LOAN_CLIENT_AUCTION: 'subastaPrestamo',
    URL_LOAN_CLIENT: 'prestamo',

    /* NEW IMPLEMENTATION */
    NAVIGATION_URLS: [
        {
            key: 'faq',
            url: 'faq'
        }
    ],
    
    /*NEW IMPLEMENTATION */
    WS_URLS: [
        // {
        //     key: 'CRUD_FAQ_URL',
        //     url: PREFIX_DOMAIN_API + 'ws-rest-parameter/faqs/'
        // }
    ]
};

