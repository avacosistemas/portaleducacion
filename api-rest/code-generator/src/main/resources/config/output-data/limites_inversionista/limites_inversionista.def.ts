import { LIMITES_INVERSIONISTA_CREATE_FORM_FIELDS_DEF } from './form/limites_inversionista.create.fields';
import { LIMITES_INVERSIONISTA_UPDATE_FORM_FIELDS_DEF } from './form/limites_inversionista.update.fields';
import { LIMITES_INVERSIONISTA_READ_FORM_FIELDS_DEF } from './form/limites_inversionista.read.fields';
import { LIMITES_INVERSIONISTA_FILTER_FORM_FIELDS_DEF } from './form/limites_inversionista.filter.fields';
import { LIMITES_INVERSIONISTA_SECURITY_DEF } from './security/limites_inversionista.security';
import { LIMITES_INVERSIONISTA_GRID_DEF } from './grid/limites_inversionista.grid';
import { LIMITES_INVERSIONISTA_I18N_DEF } from './i18n/limites_inversionista.i18n';
import { LIMITES_INVERSIONISTA_NAV_DEF } from './navigation/limites_inversionista.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const LIMITES_INVERSIONISTA_DEF: CrudDef = { 
    name: 'LIMITES_INVERSIONISTA',
    i18n: LIMITES_INVERSIONISTA_I18N_DEF,
    grid: LIMITES_INVERSIONISTA_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: LIMITES_INVERSIONISTA_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: LIMITES_INVERSIONISTA_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: LIMITES_INVERSIONISTA_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  LIMITES_INVERSIONISTA_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: LIMITES_INVERSIONISTA_NAV_DEF,
    security: LIMITES_INVERSIONISTA_SECURITY_DEF,
    ws: {
        key: 'LIMITES_INVERSIONISTA_CRUD_URL',
        url: 'http://localhost:8080/limitesInversionista/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
