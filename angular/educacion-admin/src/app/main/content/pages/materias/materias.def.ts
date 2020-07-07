import { MATERIAS_CREATE_FORM_FIELDS_DEF } from './form/materias.create.fields';
import { MATERIAS_UPDATE_FORM_FIELDS_DEF } from './form/materias.update.fields';
import { MATERIAS_READ_FORM_FIELDS_DEF } from './form/materias.read.fields';
import { MATERIAS_FILTER_FORM_FIELDS_DEF } from './form/materias.filter.fields';
import { MATERIAS_SECURITY_DEF } from './security/materias.security';
import { MATERIAS_GRID_DEF } from './grid/materias.grid';
import { MATERIAS_I18N_DEF } from './i18n/materias.i18n';
import { MATERIAS_NAV_DEF } from './navigation/materias.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';

// Definicion de un template crud(Create,Read,Update and Delete)
export const MATERIAS_DEF: CrudDef = { 
    name: 'MATERIAS',
    i18n: MATERIAS_I18N_DEF,
    grid: MATERIAS_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: MATERIAS_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: MATERIAS_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: MATERIAS_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  MATERIAS_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: MATERIAS_NAV_DEF,
    security: MATERIAS_SECURITY_DEF,
    ws: {
        key: 'MATERIAS_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-educacion/materias/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
