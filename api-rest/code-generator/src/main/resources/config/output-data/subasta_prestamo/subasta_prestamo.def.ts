import { SUBASTA_PRESTAMO_CREATE_FORM_FIELDS_DEF } from './form/subasta_prestamo.create.fields';
import { SUBASTA_PRESTAMO_UPDATE_FORM_FIELDS_DEF } from './form/subasta_prestamo.update.fields';
import { SUBASTA_PRESTAMO_READ_FORM_FIELDS_DEF } from './form/subasta_prestamo.read.fields';
import { SUBASTA_PRESTAMO_FILTER_FORM_FIELDS_DEF } from './form/subasta_prestamo.filter.fields';
import { SUBASTA_PRESTAMO_SECURITY_DEF } from './security/subasta_prestamo.security';
import { SUBASTA_PRESTAMO_GRID_DEF } from './grid/subasta_prestamo.grid';
import { SUBASTA_PRESTAMO_I18N_DEF } from './i18n/subasta_prestamo.i18n';
import { SUBASTA_PRESTAMO_NAV_DEF } from './navigation/subasta_prestamo.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const SUBASTA_PRESTAMO_DEF: CrudDef = { 
    name: 'SUBASTA_PRESTAMO',
    i18n: SUBASTA_PRESTAMO_I18N_DEF,
    grid: SUBASTA_PRESTAMO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: SUBASTA_PRESTAMO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: SUBASTA_PRESTAMO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: SUBASTA_PRESTAMO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  SUBASTA_PRESTAMO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: SUBASTA_PRESTAMO_NAV_DEF,
    security: SUBASTA_PRESTAMO_SECURITY_DEF,
    ws: {
        key: 'SUBASTA_PRESTAMO_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-prestamo/subastaprestamo/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
