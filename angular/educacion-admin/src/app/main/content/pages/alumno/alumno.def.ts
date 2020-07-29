import { ALUMNO_CREATE_FORM_FIELDS_DEF } from './form/alumno.create.fields';
import { ALUMNO_UPDATE_FORM_FIELDS_DEF } from './form/alumno.update.fields';
import { ALUMNO_READ_FORM_FIELDS_DEF } from './form/alumno.read.fields';
import { ALUMNO_FILTER_FORM_FIELDS_DEF } from './form/alumno.filter.fields';
import { ALUMNO_SECURITY_DEF } from './security/alumno.security';
import { ALUMNO_GRID_DEF } from './grid/alumno.grid';
import { ALUMNO_I18N_DEF } from './i18n/alumno.i18n';
import { ALUMNO_NAV_DEF } from './navigation/alumno.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const ALUMNO_DEF: CrudDef = { 
    name: 'ALUMNO',
    i18n: ALUMNO_I18N_DEF,
    grid: ALUMNO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        create : {
            fields: ALUMNO_CREATE_FORM_FIELDS_DEF,
            showSubmitContinue: true
        }
    },
    forms: {
        filter: ALUMNO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        // create: ALUMNO_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: ALUMNO_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  ALUMNO_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: ALUMNO_NAV_DEF,
    security: ALUMNO_SECURITY_DEF,
    ws: {
        key: 'ALUMNO_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/alumnos/'
    },
    dialogConfig: {
        width: '500px'
    }
};
