import { TAG_SELECTION_CREATE_FORM_FIELDS_DEF } from './form/tag_selection.create.fields';
import { TAG_SELECTION_UPDATE_FORM_FIELDS_DEF } from './form/tag_selection.update.fields';
import { TAG_SELECTION_READ_FORM_FIELDS_DEF } from './form/tag_selection.read.fields';
import { TAG_SELECTION_FILTER_FORM_FIELDS_DEF } from './form/tag_selection.filter.fields';
import { TAG_SELECTION_SECURITY_DEF } from './security/tag_selection.security';
import { TAG_SELECTION_GRID_DEF } from './grid/tag_selection.grid';
import { TAG_SELECTION_I18N_DEF } from './i18n/tag_selection.i18n';
import { TAG_SELECTION_NAV_DEF } from './navigation/tag_selection.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const TAG_SELECTION_DEF: CrudDef = { 
    name: 'TAG_SELECTION',
    i18n: TAG_SELECTION_I18N_DEF,
    grid: TAG_SELECTION_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: TAG_SELECTION_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: TAG_SELECTION_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: TAG_SELECTION_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  TAG_SELECTION_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: TAG_SELECTION_NAV_DEF,
    security: TAG_SELECTION_SECURITY_DEF,
    ws: {
        key: 'TAG_SELECTION_CRUD_URL',
        url: 'http://localhost:5000/api/TagSelection/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
