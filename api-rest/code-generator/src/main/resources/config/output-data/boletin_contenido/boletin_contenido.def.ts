import { BOLETIN_CONTENIDO_CREATE_FORM_FIELDS_DEF } from './form/boletin_contenido.create.fields';
import { BOLETIN_CONTENIDO_UPDATE_FORM_FIELDS_DEF } from './form/boletin_contenido.update.fields';
import { BOLETIN_CONTENIDO_READ_FORM_FIELDS_DEF } from './form/boletin_contenido.read.fields';
import { BOLETIN_CONTENIDO_FILTER_FORM_FIELDS_DEF } from './form/boletin_contenido.filter.fields';
import { BOLETIN_CONTENIDO_SECURITY_DEF } from './security/boletin_contenido.security';
import { BOLETIN_CONTENIDO_GRID_DEF } from './grid/boletin_contenido.grid';
import { BOLETIN_CONTENIDO_I18N_DEF } from './i18n/boletin_contenido.i18n';
import { BOLETIN_CONTENIDO_NAV_DEF } from './navigation/boletin_contenido.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const BOLETIN_CONTENIDO_DEF: CrudDef = { 
    name: 'BOLETIN_CONTENIDO',
    i18n: BOLETIN_CONTENIDO_I18N_DEF,
    grid: BOLETIN_CONTENIDO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: BOLETIN_CONTENIDO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: BOLETIN_CONTENIDO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: BOLETIN_CONTENIDO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  BOLETIN_CONTENIDO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: BOLETIN_CONTENIDO_NAV_DEF,
    security: BOLETIN_CONTENIDO_SECURITY_DEF,
    ws: {
        key: 'BOLETIN_CONTENIDO_CRUD_URL',
        url: 'http://localhost:5000/api/BoletinContenido/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
