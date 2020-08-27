import { WORD_CREATE_FORM_FIELDS_DEF } from './form/word.create.fields';
import { WORD_UPDATE_FORM_FIELDS_DEF } from './form/word.update.fields';
import { WORD_READ_FORM_FIELDS_DEF } from './form/word.read.fields';
import { WORD_FILTER_FORM_FIELDS_DEF } from './form/word.filter.fields';
import { WORD_SECURITY_DEF } from './security/word.security';
import { WORD_GRID_DEF } from './grid/word.grid';
import { WORD_I18N_DEF } from './i18n/word.i18n';
import { WORD_NAV_DEF } from './navigation/word.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const WORD_DEF: CrudDef = { 
    name: 'WORD',
    i18n: WORD_I18N_DEF,
    grid: WORD_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: WORD_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: WORD_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: WORD_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  WORD_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: WORD_NAV_DEF,
    security: WORD_SECURITY_DEF,
    ws: {
        key: 'WORD_CRUD_URL',
        url: 'http://localhost:8080/words/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
