import { MATERIA_PROFESOR_CREATE_FORM_FIELDS_DEF } from './form/materia_profesor.create.fields';
import { MATERIA_PROFESOR_UPDATE_FORM_FIELDS_DEF } from './form/materia_profesor.update.fields';
import { MATERIA_PROFESOR_READ_FORM_FIELDS_DEF } from './form/materia_profesor.read.fields';
import { MATERIA_PROFESOR_FILTER_FORM_FIELDS_DEF } from './form/materia_profesor.filter.fields';
import { MATERIA_PROFESOR_SECURITY_DEF } from './security/materia_profesor.security';
import { MATERIA_PROFESOR_GRID_DEF } from './grid/materia_profesor.grid';
import { MATERIA_PROFESOR_I18N_DEF } from './i18n/materia_profesor.i18n';
import { MATERIA_PROFESOR_NAV_DEF } from './navigation/materia_profesor.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const MATERIA_PROFESOR_DEF: CrudDef = { 
    name: 'MATERIA_PROFESOR',
    i18n: MATERIA_PROFESOR_I18N_DEF,
    grid: MATERIA_PROFESOR_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        create: {
            fields: MATERIA_PROFESOR_CREATE_FORM_FIELDS_DEF,
            showSubmitContinue: true
        }
    },
    forms: {
        filter: MATERIA_PROFESOR_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        // create: MATERIA_PROFESOR_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        // update: MATERIA_PROFESOR_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        // read:  MATERIA_PROFESOR_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: MATERIA_PROFESOR_NAV_DEF,
    security: MATERIA_PROFESOR_SECURITY_DEF,
    ws: {
        key: 'MATERIA_PROFESOR_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/materiasprofesor/'
    },
    dialogConfig: {
        width: '500px'
    },
    filterInMemory: false,
    backButton: true
};
