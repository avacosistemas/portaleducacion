import { PRESTAMO_CREATE_FORM_FIELDS_DEF } from './form/prestamo.create.fields';
import { PRESTAMO_UPDATE_FORM_FIELDS_DEF } from './form/prestamo.update.fields';
import { PRESTAMO_READ_FORM_FIELDS_DEF } from './form/prestamo.read.fields';
import { PRESTAMO_FILTER_FORM_FIELDS_DEF } from './form/prestamo.filter.fields';
import { PRESTAMO_SECURITY_DEF } from './security/prestamo.security';
import { PRESTAMO_GRID_DEF } from './grid/prestamo.grid';
import { PRESTAMO_I18N_DEF } from './i18n/prestamo.i18n';
import { PRESTAMO_NAV_DEF } from './navigation/prestamo.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const PRESTAMO_DEF: CrudDef = { 
    name: 'PRESTAMO',
    i18n: PRESTAMO_I18N_DEF,
    grid: PRESTAMO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: PRESTAMO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: PRESTAMO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: PRESTAMO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  PRESTAMO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: PRESTAMO_NAV_DEF,
    security: PRESTAMO_SECURITY_DEF,
    ws: {
        key: 'PRESTAMO_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-prestamo/prestamo/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
