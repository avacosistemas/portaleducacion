import { OPCION_CREATE_FORM_FIELDS_DEF } from './form/opcion.create.fields';
import { OPCION_UPDATE_FORM_FIELDS_DEF } from './form/opcion.update.fields';
import { OPCION_READ_FORM_FIELDS_DEF } from './form/opcion.read.fields';
import { OPCION_FILTER_FORM_FIELDS_DEF } from './form/opcion.filter.fields';
import { OPCION_SECURITY_DEF } from './security/opcion.security';
import { OPCION_GRID_DEF } from './grid/opcion.grid';
import { OPCION_I18N_DEF } from './i18n/opcion.i18n';
import { OPCION_NAV_DEF } from './navigation/opcion.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const OPCION_DEF: CrudDef = { 
    name: 'OPCION',
    i18n: OPCION_I18N_DEF,
    grid: OPCION_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: OPCION_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: OPCION_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: OPCION_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  OPCION_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: OPCION_NAV_DEF,
    security: OPCION_SECURITY_DEF,
    ws: {
        key: 'OPCION_CRUD_URL',
        url: 'http://localhost:5000/api/Opcion/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
