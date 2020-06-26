import { PARAMETER_CREATE_FORM_FIELDS_DEF } from './form/parameter.create.fields';
import { PARAMETER_UPDATE_FORM_FIELDS_DEF } from './form/parameter.update.fields';
import { PARAMETER_READ_FORM_FIELDS_DEF } from './form/parameter.read.fields';
import { PARAMETER_FILTER_FORM_FIELDS_DEF } from './form/parameter.filter.fields';
import { PARAMETER_SECURITY_DEF } from './security/parameter.security';
import { PARAMETER_GRID_DEF } from './grid/parameter.grid';
import { PARAMETER_I18N_DEF } from './i18n/parameter.i18n';
import { PARAMETER_NAV_DEF } from './navigation/parameter.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';

// Definicion de un template crud(Create,Read,Update and Delete)
export const PARAMETER_DEF: CrudDef = { 
    name: 'PARAMETER',
    i18n: PARAMETER_I18N_DEF,
    grid: PARAMETER_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: PARAMETER_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: PARAMETER_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: PARAMETER_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  PARAMETER_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: PARAMETER_NAV_DEF,
    security: PARAMETER_SECURITY_DEF,
    ws: {
        key: 'PARAMETER_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-parameter/parameters/'
    },
    dialogConfig: {
        width: '400px'
    }
};
