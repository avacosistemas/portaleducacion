import { BANNER_CAMPAING_CREATE_FORM_FIELDS_DEF } from './form/banner_campaing.create.fields';
import { BANNER_CAMPAING_UPDATE_FORM_FIELDS_DEF } from './form/banner_campaing.update.fields';
import { BANNER_CAMPAING_READ_FORM_FIELDS_DEF } from './form/banner_campaing.read.fields';
import { BANNER_CAMPAING_FILTER_FORM_FIELDS_DEF } from './form/banner_campaing.filter.fields';
import { BANNER_CAMPAING_SECURITY_DEF } from './security/banner_campaing.security';
import { BANNER_CAMPAING_GRID_DEF } from './grid/banner_campaing.grid';
import { BANNER_CAMPAING_I18N_DEF } from './i18n/banner_campaing.i18n';
import { BANNER_CAMPAING_NAV_DEF } from './navigation/banner_campaing.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const BANNER_CAMPAING_DEF: CrudDef = { 
    name: 'BANNER_CAMPAING',
    i18n: BANNER_CAMPAING_I18N_DEF,
    grid: BANNER_CAMPAING_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: BANNER_CAMPAING_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: BANNER_CAMPAING_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: BANNER_CAMPAING_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  BANNER_CAMPAING_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: BANNER_CAMPAING_NAV_DEF,
    security: BANNER_CAMPAING_SECURITY_DEF,
    ws: {
        key: 'BANNER_CAMPAING_CRUD_URL',
        url: 'http://localhost:5000/api/BannerCampaign/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
