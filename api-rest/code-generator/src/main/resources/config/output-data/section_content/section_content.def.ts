import { SECTION_CONTENT_CREATE_FORM_FIELDS_DEF } from './form/section_content.create.fields';
import { SECTION_CONTENT_UPDATE_FORM_FIELDS_DEF } from './form/section_content.update.fields';
import { SECTION_CONTENT_READ_FORM_FIELDS_DEF } from './form/section_content.read.fields';
import { SECTION_CONTENT_FILTER_FORM_FIELDS_DEF } from './form/section_content.filter.fields';
import { SECTION_CONTENT_SECURITY_DEF } from './security/section_content.security';
import { SECTION_CONTENT_GRID_DEF } from './grid/section_content.grid';
import { SECTION_CONTENT_I18N_DEF } from './i18n/section_content.i18n';
import { SECTION_CONTENT_NAV_DEF } from './navigation/section_content.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const SECTION_CONTENT_DEF: CrudDef = { 
    name: 'SECTION_CONTENT',
    i18n: SECTION_CONTENT_I18N_DEF,
    grid: SECTION_CONTENT_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: SECTION_CONTENT_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: SECTION_CONTENT_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: SECTION_CONTENT_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  SECTION_CONTENT_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: SECTION_CONTENT_NAV_DEF,
    security: SECTION_CONTENT_SECURITY_DEF,
    ws: {
        key: 'SECTION_CONTENT_CRUD_URL',
        url: 'http://localhost:5000/api/SectionContent/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
