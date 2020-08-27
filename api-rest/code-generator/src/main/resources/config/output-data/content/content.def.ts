import { CONTENT_CREATE_FORM_FIELDS_DEF } from './form/content.create.fields';
import { CONTENT_UPDATE_FORM_FIELDS_DEF } from './form/content.update.fields';
import { CONTENT_READ_FORM_FIELDS_DEF } from './form/content.read.fields';
import { CONTENT_FILTER_FORM_FIELDS_DEF } from './form/content.filter.fields';
import { CONTENT_SECURITY_DEF } from './security/content.security';
import { CONTENT_GRID_DEF } from './grid/content.grid';
import { CONTENT_I18N_DEF } from './i18n/content.i18n';
import { CONTENT_NAV_DEF } from './navigation/content.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const CONTENT_DEF: CrudDef = { 
    name: 'CONTENT',
    i18n: CONTENT_I18N_DEF,
    grid: CONTENT_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: CONTENT_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: CONTENT_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: CONTENT_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  CONTENT_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: CONTENT_NAV_DEF,
    security: CONTENT_SECURITY_DEF,
    ws: {
        key: 'CONTENT_CRUD_URL',
        url: 'http://localhost:5000/api/Content/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
