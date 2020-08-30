import { EVENTO_AULA_CREATE_FORM_FIELDS_DEF } from './form/evento_aula.create.fields';
import { EVENTO_AULA_UPDATE_FORM_FIELDS_DEF } from './form/evento_aula.update.fields';
import { EVENTO_AULA_READ_FORM_FIELDS_DEF } from './form/evento_aula.read.fields';
import { EVENTO_AULA_FILTER_FORM_FIELDS_DEF } from './form/evento_aula.filter.fields';
import { EVENTO_AULA_SECURITY_DEF } from './security/evento_aula.security';
import { EVENTO_AULA_GRID_DEF } from './grid/evento_aula.grid';
import { EVENTO_AULA_I18N_DEF } from './i18n/evento_aula.i18n';
import { EVENTO_AULA_NAV_DEF } from './navigation/evento_aula.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const EVENTO_AULA_DEF: CrudDef = { 
    name: 'EVENTO_AULA',
    i18n: EVENTO_AULA_I18N_DEF,
    grid: EVENTO_AULA_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: EVENTO_AULA_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        // create: EVENTO_AULA_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        // update: EVENTO_AULA_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  EVENTO_AULA_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: EVENTO_AULA_NAV_DEF,
    security: EVENTO_AULA_SECURITY_DEF,
    ws: {
        key: 'EVENTO_AULA_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION +  '/event/aula/registroEventos'
    },
    dialogConfig: {
        width: '400px'
    },
    backButton: true
};
