import { SOLICITUD_PRESTAMO_SOLICITANTE_CREATE_FORM_FIELDS_DEF } from './form/solicitud_prestamo_solicitante.create.fields';
import { SOLICITUD_PRESTAMO_SOLICITANTE_UPDATE_FORM_FIELDS_DEF } from './form/solicitud_prestamo_solicitante.update.fields';
import { SOLICITUD_PRESTAMO_SOLICITANTE_READ_FORM_FIELDS_DEF } from './form/solicitud_prestamo_solicitante.read.fields';
import { SOLICITUD_PRESTAMO_SOLICITANTE_FILTER_FORM_FIELDS_DEF } from './form/solicitud_prestamo_solicitante.filter.fields';
import { SOLICITUD_PRESTAMO_SOLICITANTE_SECURITY_DEF } from './security/solicitud_prestamo_solicitante.security';
import { SOLICITUD_PRESTAMO_SOLICITANTE_GRID_DEF } from './grid/solicitud_prestamo_solicitante.grid';
import { SOLICITUD_PRESTAMO_SOLICITANTE_I18N_DEF } from './i18n/solicitud_prestamo_solicitante.i18n';
import { SOLICITUD_PRESTAMO_SOLICITANTE_NAV_DEF } from './navigation/solicitud_prestamo_solicitante.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const SOLICITUD_PRESTAMO_SOLICITANTE_DEF: CrudDef = { 
    name: 'SOLICITUD_PRESTAMO_SOLICITANTE',
    i18n: SOLICITUD_PRESTAMO_SOLICITANTE_I18N_DEF,
    grid: SOLICITUD_PRESTAMO_SOLICITANTE_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: SOLICITUD_PRESTAMO_SOLICITANTE_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: SOLICITUD_PRESTAMO_SOLICITANTE_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: SOLICITUD_PRESTAMO_SOLICITANTE_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  SOLICITUD_PRESTAMO_SOLICITANTE_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: SOLICITUD_PRESTAMO_SOLICITANTE_NAV_DEF,
    security: SOLICITUD_PRESTAMO_SOLICITANTE_SECURITY_DEF,
    ws: {
        key: 'SOLICITUD_PRESTAMO_SOLICITANTE_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-prestamo-cliente/solicitudprestamo/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
