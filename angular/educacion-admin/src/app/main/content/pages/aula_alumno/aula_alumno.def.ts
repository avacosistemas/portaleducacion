import { AULA_ALUMNO_CREATE_FORM_FIELDS_DEF } from './form/aula_alumno.create.fields';
import { AULA_ALUMNO_UPDATE_FORM_FIELDS_DEF } from './form/aula_alumno.update.fields';
import { AULA_ALUMNO_READ_FORM_FIELDS_DEF } from './form/aula_alumno.read.fields';
import { AULA_ALUMNO_FILTER_FORM_FIELDS_DEF } from './form/aula_alumno.filter.fields';
import { AULA_ALUMNO_SECURITY_DEF } from './security/aula_alumno.security';
import { AULA_ALUMNO_GRID_DEF } from './grid/aula_alumno.grid';
import { AULA_ALUMNO_I18N_DEF } from './i18n/aula_alumno.i18n';
import { AULA_ALUMNO_NAV_DEF } from './navigation/aula_alumno.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const AULA_ALUMNO_DEF: CrudDef = { 
    name: 'AULA_ALUMNO',
    i18n: AULA_ALUMNO_I18N_DEF,
    grid: AULA_ALUMNO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        create: {
            fields: AULA_ALUMNO_CREATE_FORM_FIELDS_DEF,
            showSubmitContinue: true 
        }
    },
    forms: {
        filter: AULA_ALUMNO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        //create: AULA_ALUMNO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        // update: AULA_ALUMNO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        // read:  AULA_ALUMNO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: AULA_ALUMNO_NAV_DEF,
    security: AULA_ALUMNO_SECURITY_DEF,
    ws: {
        key: 'AULA_ALUMNO_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/aulaAlumno/'
    },
    dialogConfig: {
        width: '500px'
    },
    backButton: true,
};
