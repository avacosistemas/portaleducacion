import { IMAGEN_CREATE_FORM_FIELDS_DEF } from './form/imagen.create.fields';
import { IMAGEN_UPDATE_FORM_FIELDS_DEF } from './form/imagen.update.fields';
import { IMAGEN_READ_FORM_FIELDS_DEF } from './form/imagen.read.fields';
import { IMAGEN_FILTER_FORM_FIELDS_DEF } from './form/imagen.filter.fields';
import { IMAGEN_SECURITY_DEF } from './security/imagen.security';
import { IMAGEN_GRID_DEF } from './grid/imagen.grid';
import { IMAGEN_I18N_DEF } from './i18n/imagen.i18n';
import { IMAGEN_NAV_DEF } from './navigation/imagen.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const IMAGEN_DEF: CrudDef = { 
    name: 'IMAGEN',
    i18n: IMAGEN_I18N_DEF,
    grid: IMAGEN_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: IMAGEN_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: IMAGEN_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: IMAGEN_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  IMAGEN_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: IMAGEN_NAV_DEF,
    security: IMAGEN_SECURITY_DEF,
    ws: {
        key: 'IMAGEN_CRUD_URL',
        url: 'http://localhost:5000/api/Image/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
