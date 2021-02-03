// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

// ENTORNO LOCAL
// export const PREFIX_DOMAIN_API_EDUCACION =  'http://localhost:8080/ws-rest-educacion';
// export const PREFIX_DOMAIN_API_AUTHENTICATION =  'http://localhost:8080/ws-rest-authentication';

// ENTORNO PRODUCCION
export const PREFIX_DOMAIN_API_EDUCACION =  'https://api.teachonline.com.ar/ws-rest-educacion';
export const PREFIX_DOMAIN_API_AUTHENTICATION =  'https://api.teachonline.com.ar/ws-rest-authentication';

export const environment = {
    localAuth: true,

    production: true,
    hmr       : false,
    // pasar a true para evitar ws
    /** URLs of Services **/

    /** AUTHENTICATION */
    AUTHENTICATION_URL:  PREFIX_DOMAIN_API_AUTHENTICATION + '/auth',
    AUTHENTICATION_REFRESH_TOKEN_URL:  PREFIX_DOMAIN_API_AUTHENTICATION + '/refresh',
    PERMISSION_CRUD_URL: PREFIX_DOMAIN_API_AUTHENTICATION + '/permissions/',

    PROFILE_CRUD_URL: PREFIX_DOMAIN_API_AUTHENTICATION + '/profiles/',
    ROLE_CRUD_URL :  PREFIX_DOMAIN_API_AUTHENTICATION + '/roles/',
    USER_CRUD_URL :  PREFIX_DOMAIN_API_AUTHENTICATION + '/users/',
    USER_VALIDATION_ADD_URL : PREFIX_DOMAIN_API_AUTHENTICATION + '/validationAdd',
    USER_VALIDATION_EDIT_URL  : PREFIX_DOMAIN_API_AUTHENTICATION + '/validationEdit',

    /* Config General URl*/
    URL_ROOT: '',
    URL_LOGIN: '/auth/login',
    // Business
    URL_PARAMETERS : 'parameters',
    URL_FAQS : 'faqs',
    URL_INSTITUCIONES : 'instituciones',
    URL_MATERIAS : 'materias',
    URL_AULAS : 'aulas',
    URL_PROFESORES : 'profesores',
    URL_ALUMNOS : 'alumnos',
    URL_LOGOUT_API: PREFIX_DOMAIN_API_AUTHENTICATION + '/logout',
    URL_PERMISSION : 'permisos',
    URL_PROFILE : 'perfiles',
    URL_ROLE : 'roles',
    URL_USER : 'usuarios',
    URL_PASSWORD_UPDATE: 'password'

};

