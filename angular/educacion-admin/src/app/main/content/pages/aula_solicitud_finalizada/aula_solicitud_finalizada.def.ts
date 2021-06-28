import { AULA_SOLICITUD_FINALIZADA_CREATE_FORM_FIELDS_DEF } from './form/aula_solicitud_finalizada.create.fields';
import { AULA_SOLICITUD_FINALIZADA_UPDATE_FORM_FIELDS_DEF } from './form/aula_solicitud_finalizada.update.fields';
import { AULA_SOLICITUD_FINALIZADA_READ_FORM_FIELDS_DEF } from './form/aula_solicitud_finalizada.read.fields';
import { AULA_SOLICITUD_FINALIZADA_FILTER_FORM_FIELDS_DEF } from './form/aula_solicitud_finalizada.filter.fields';
import { AULA_SOLICITUD_FINALIZADA_SECURITY_DEF } from './security/aula_solicitud_finalizada.security';
import { AULA_SOLICITUD_FINALIZADA_GRID_DEF } from './grid/aula_solicitud_finalizada.grid';
import { AULA_SOLICITUD_FINALIZADA_I18N_DEF } from './i18n/aula_solicitud_finalizada.i18n';
import { AULA_SOLICITUD_FINALIZADA_NAV_DEF } from './navigation/aula_solicitud_finalizada.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API_EDUCACION } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const AULA_SOLICITUD_FINALIZADA_DEF: CrudDef = { 
    name: 'AULA_SOLICITUD_FINALIZADA',
    i18n: AULA_SOLICITUD_FINALIZADA_I18N_DEF,
    grid: AULA_SOLICITUD_FINALIZADA_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: AULA_SOLICITUD_FINALIZADA_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        // create: AULA_SOLICITUD_FINALIZADA_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        // update: AULA_SOLICITUD_FINALIZADA_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        // read:  AULA_SOLICITUD_FINALIZADA_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: AULA_SOLICITUD_FINALIZADA_NAV_DEF,
    security: AULA_SOLICITUD_FINALIZADA_SECURITY_DEF,
    ws: {
        key: 'AULA_SOLICITUD_FINALIZADA_CRUD_URL',
        url: PREFIX_DOMAIN_API_EDUCACION +  '/solicitudAulaFinalizada'
    },
    dialogConfig: {
        width: '400px'
    }   
};
