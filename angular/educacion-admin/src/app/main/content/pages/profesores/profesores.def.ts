import { PROFESORES_CREATE_FORM_FIELDS_DEF } from './form/profesores.create.fields';
import { PROFESORES_UPDATE_FORM_FIELDS_DEF } from './form/profesores.update.fields';
import { PROFESORES_READ_FORM_FIELDS_DEF } from './form/profesores.read.fields';
import { PROFESORES_FILTER_FORM_FIELDS_DEF } from './form/profesores.filter.fields';
import { PROFESORES_SECURITY_DEF } from './security/profesores.security';
import { PROFESORES_GRID_DEF } from './grid/profesores.grid';
import { PROFESORES_I18N_DEF } from './i18n/profesores.i18n';
import { PROFESORES_NAV_DEF } from './navigation/profesores.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';

// Definicion de un template crud(Create,Read,Update and Delete)
export const PROFESORES_DEF: CrudDef = { 
    name: 'PROFESORES',
    i18n: PROFESORES_I18N_DEF,
    grid: PROFESORES_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        create: {
            fields: PROFESORES_CREATE_FORM_FIELDS_DEF,
            showSubmitContinue: true 
        }
    },
    forms: {
        filter: PROFESORES_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        // create: PROFESORES_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: PROFESORES_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  PROFESORES_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: PROFESORES_NAV_DEF,
    security: PROFESORES_SECURITY_DEF,
    ws: {
        key: 'PROFESORES_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-educacion/profesores/'
    },
    dialogConfig: {
        width: '800px'
    }   
};
