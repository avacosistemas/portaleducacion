import { FAQS_CREATE_FORM_FIELDS_DEF } from './form/faqs.create.fields';
import { FAQS_UPDATE_FORM_FIELDS_DEF } from './form/faqs.update.fields';
import { FAQS_READ_FORM_FIELDS_DEF } from './form/faqs.read.fields';
import { FAQS_FILTER_FORM_FIELDS_DEF } from './form/faqs.filter.fields';
import { FAQS_SECURITY_DEF } from './security/faqs.security';
import { FAQS_GRID_DEF } from './grid/faqs.grid';
import { FAQS_I18N_DEF } from './i18n/faqs.i18n';
import { FAQS_NAV_DEF } from './navigation/faqs.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const FAQS_DEF: CrudDef = {
    name: 'FAQS',
    i18n: FAQS_I18N_DEF,
    grid: FAQS_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: FAQS_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: FAQS_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: FAQS_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  FAQS_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: FAQS_NAV_DEF,
    security: FAQS_SECURITY_DEF,
    ws: {
        key: 'FAQS_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/faqs/'
    },
    dialogConfig: {
        width: '400px'
    }
};
