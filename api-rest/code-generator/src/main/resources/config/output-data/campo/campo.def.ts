import { CAMPO_CREATE_FORM_FIELDS_DEF } from './form/campo.create.fields';
import { CAMPO_UPDATE_FORM_FIELDS_DEF } from './form/campo.update.fields';
import { CAMPO_READ_FORM_FIELDS_DEF } from './form/campo.read.fields';
import { CAMPO_FILTER_FORM_FIELDS_DEF } from './form/campo.filter.fields';
import { CAMPO_SECURITY_DEF } from './security/campo.security';
import { CAMPO_GRID_DEF } from './grid/campo.grid';
import { CAMPO_I18N_DEF } from './i18n/campo.i18n';
import { CAMPO_NAV_DEF } from './navigation/campo.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const CAMPO_DEF: CrudDef = { 
    name: 'CAMPO',
    i18n: CAMPO_I18N_DEF,
    grid: CAMPO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: CAMPO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: CAMPO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: CAMPO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  CAMPO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: CAMPO_NAV_DEF,
    security: CAMPO_SECURITY_DEF,
    ws: {
        key: 'CAMPO_CRUD_URL',
        url: 'http://localhost:5000/api/Campo/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
