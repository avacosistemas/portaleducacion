import { BANNER_CREATE_FORM_FIELDS_DEF } from './form/banner.create.fields';
import { BANNER_UPDATE_FORM_FIELDS_DEF } from './form/banner.update.fields';
import { BANNER_READ_FORM_FIELDS_DEF } from './form/banner.read.fields';
import { BANNER_FILTER_FORM_FIELDS_DEF } from './form/banner.filter.fields';
import { BANNER_SECURITY_DEF } from './security/banner.security';
import { BANNER_GRID_DEF } from './grid/banner.grid';
import { BANNER_I18N_DEF } from './i18n/banner.i18n';
import { BANNER_NAV_DEF } from './navigation/banner.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const BANNER_DEF: CrudDef = { 
    name: 'BANNER',
    i18n: BANNER_I18N_DEF,
    grid: BANNER_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: BANNER_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: BANNER_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: BANNER_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  BANNER_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: BANNER_NAV_DEF,
    security: BANNER_SECURITY_DEF,
    ws: {
        key: 'BANNER_CRUD_URL',
        url: 'http://localhost:5000/api/Banner/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
