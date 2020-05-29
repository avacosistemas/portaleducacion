import { PRODUCTO_EXTERNO_CREATE_FORM_FIELDS_DEF } from './form/producto_externo.create.fields';
import { PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF } from './form/producto_externo.update.fields';
import { PRODUCTO_EXTERNO_READ_FORM_FIELDS_DEF } from './form/producto_externo.read.fields';
import { PRODUCTO_EXTERNO_FILTER_FORM_FIELDS_DEF } from './form/producto_externo.filter.fields';
import { PRODUCTO_EXTERNO_SECURITY_DEF } from './security/producto_externo.security';
import { PRODUCTO_EXTERNO_GRID_DEF } from './grid/producto_externo.grid';
import { PRODUCTO_EXTERNO_I18N_DEF } from './i18n/producto_externo.i18n';
import { PRODUCTO_EXTERNO_NAV_DEF } from './navigation/producto_externo.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API } from 'environments/environment';

// Definicion de un template crud(Create,Read,Update and Delete)
export const PRODUCTO_EXTERNO_DEF: CrudDef = { 
    name: 'PRODUCTO_EXTERNO',
    i18n: PRODUCTO_EXTERNO_I18N_DEF,
    grid: PRODUCTO_EXTERNO_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    formsDef: {
        
      update: {
          fields: PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF,
          showSubmitContinue: true
      }  
    },
    forms: {
        filter: PRODUCTO_EXTERNO_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
    },
    navigation: PRODUCTO_EXTERNO_NAV_DEF,
    security: PRODUCTO_EXTERNO_SECURITY_DEF,
    ws: {
        key: 'PRODUCTO_EXTERNO_CRUD_URL',
        url: PREFIX_DOMAIN_API + 'ExternalProduct'
    },
    dialogConfig: {
        width: '800px'
    }   
};
