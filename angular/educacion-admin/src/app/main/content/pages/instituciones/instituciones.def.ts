import { INSTITUCIONES_CREATE_FORM_FIELDS_DEF } from './form/instituciones.create.fields';
import { INSTITUCIONES_UPDATE_FORM_FIELDS_DEF } from './form/instituciones.update.fields';
import { INSTITUCIONES_READ_FORM_FIELDS_DEF } from './form/instituciones.read.fields';
import { INSTITUCIONES_FILTER_FORM_FIELDS_DEF } from './form/instituciones.filter.fields';
import { INSTITUCIONES_SECURITY_DEF } from './security/instituciones.security';
import { INSTITUCIONES_GRID_DEF } from './grid/instituciones.grid';
import { INSTITUCIONES_I18N_DEF } from './i18n/instituciones.i18n';
import { INSTITUCIONES_NAV_DEF } from './navigation/instituciones.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const INSTITUCIONES_DEF: CrudDef = { 
    name: 'INSTITUCIONES',
    i18n: INSTITUCIONES_I18N_DEF,
    grid: INSTITUCIONES_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        create: {
            showSubmitContinue: true,
            fields: INSTITUCIONES_CREATE_FORM_FIELDS_DEF
        }
    },
    forms: {
        filter: INSTITUCIONES_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        //create: INSTITUCIONES_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: INSTITUCIONES_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  INSTITUCIONES_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: INSTITUCIONES_NAV_DEF,
    security: INSTITUCIONES_SECURITY_DEF,
    ws: {
        key: 'INSTITUCIONES_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/instituciones'
    },
    dialogConfig: {
        width: '800px'
    }
};
