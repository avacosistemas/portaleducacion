// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const PREFIX_DOMAIN_API =  'http://localhost:8080/';

export const environment = {
    localAuth: false,
    
    production: false,
    hmr       : false,
    // pasar a true para evitar ws
    /** URLs of Services **/

    /** AUTHENTICATION */
    AUTHENTICATION_URL:  PREFIX_DOMAIN_API + '/ws-rest-authentication/auth',
    AUTHENTICATION_REFRESH_TOKEN_URL:  PREFIX_DOMAIN_API + '/ws-rest-authentication/refresh',

    /* Config General URl*/
    URL_ROOT: '',
    URL_LOGIN: '/auth/login',
    // Business
    URL_SECCIONES: 'seccion',
    URL_CONTENIDOS: 'contenido',
    URL_IMAGEN: 'imagen',
    URL_BOLETIN: 'boletin',
    URL_INDICE: 'indice',
    URL_MAILING: 'mailing',
    URL_FORMULARIO: 'formulario',
    URL_PUBLICIDAD: 'bannerCampaing',
    URL_PRODUCTO_EXTERNO : 'productoExterno',
    URL_PARAMETERS : 'parameters',
    URL_LOGOUT_API: PREFIX_DOMAIN_API + 'user/logout',
};

