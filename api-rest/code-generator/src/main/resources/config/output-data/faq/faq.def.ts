import { FAQ_CREATE_FORM_FIELDS_DEF } from './form/faq.create.fields';
import { FAQ_UPDATE_FORM_FIELDS_DEF } from './form/faq.update.fields';
import { FAQ_READ_FORM_FIELDS_DEF } from './form/faq.read.fields';
import { FAQ_FILTER_FORM_FIELDS_DEF } from './form/faq.filter.fields';
import { FAQ_SECURITY_DEF } from './security/faq.security';
import { FAQ_GRID_DEF } from './grid/faq.grid';
import { FAQ_I18N_DEF } from './i18n/faq.i18n';
import { FAQ_NAV_DEF } from './navigation/faq.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const FAQ_DEF: CrudDef = { 
    name: 'FAQ',
    i18n: FAQ_I18N_DEF,
    grid: FAQ_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: FAQ_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: FAQ_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: FAQ_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  FAQ_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: FAQ_NAV_DEF,
    security: FAQ_SECURITY_DEF,
    ws: {
        key: 'FAQ_CRUD_URL',
        url: 'http://localhost:8080/faq/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
