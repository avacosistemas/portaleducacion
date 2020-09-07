import { AULAS_CREATE_FORM_FIELDS_DEF } from './form/aulas.create.fields';
import { AULAS_UPDATE_FORM_FIELDS_DEF } from './form/aulas.update.fields';
import { AULAS_READ_FORM_FIELDS_DEF } from './form/aulas.read.fields';
import { AULAS_FILTER_FORM_FIELDS_DEF } from './form/aulas.filter.fields';
import { AULAS_SECURITY_DEF } from './security/aulas.security';
import { AULAS_GRID_DEF } from './grid/aulas.grid';
import { AULAS_I18N_DEF } from './i18n/aulas.i18n';
import { AULAS_NAV_DEF } from './navigation/aulas.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const AULAS_DEF: CrudDef = { 
    name: 'AULAS',
    i18n: AULAS_I18N_DEF,
    grid: AULAS_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: AULAS_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: AULAS_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: AULAS_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  AULAS_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: AULAS_NAV_DEF,
    security: AULAS_SECURITY_DEF,
    ws: {
        key: 'AULAS_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION + '/aulas/'
    },
    dialogConfig: {
        width: '600px'
    }   
};
