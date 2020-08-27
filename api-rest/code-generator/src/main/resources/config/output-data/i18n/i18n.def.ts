import { I18N_CREATE_FORM_FIELDS_DEF } from './form/i18n.create.fields';
import { I18N_UPDATE_FORM_FIELDS_DEF } from './form/i18n.update.fields';
import { I18N_READ_FORM_FIELDS_DEF } from './form/i18n.read.fields';
import { I18N_FILTER_FORM_FIELDS_DEF } from './form/i18n.filter.fields';
import { I18N_SECURITY_DEF } from './security/i18n.security';
import { I18N_GRID_DEF } from './grid/i18n.grid';
import { I18N_I18N_DEF } from './i18n/i18n.i18n';
import { I18N_NAV_DEF } from './navigation/i18n.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const I18N_DEF: CrudDef = { 
    name: 'I18N',
    i18n: I18N_I18N_DEF,
    grid: I18N_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: I18N_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: I18N_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: I18N_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  I18N_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: I18N_NAV_DEF,
    security: I18N_SECURITY_DEF,
    ws: {
        key: 'I18N_CRUD_URL',
        url: 'http://localhost:8080/i18ns/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
