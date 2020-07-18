import { HORAS_PROFESOR_CREATE_FORM_FIELDS_DEF } from './form/horas_profesor.create.fields';
import { HORAS_PROFESOR_UPDATE_FORM_FIELDS_DEF } from './form/horas_profesor.update.fields';
import { HORAS_PROFESOR_READ_FORM_FIELDS_DEF } from './form/horas_profesor.read.fields';
import { HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF } from './form/horas_profesor.filter.fields';
import { HORAS_PROFESOR_SECURITY_DEF } from './security/horas_profesor.security';
import { HORAS_PROFESOR_GRID_DEF } from './grid/horas_profesor.grid';
import { HORAS_PROFESOR_I18N_DEF } from './i18n/horas_profesor.i18n';
import { HORAS_PROFESOR_NAV_DEF } from './navigation/horas_profesor.nav';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { PREFIX_DOMAIN_API } from "environments/environment";

// Definicion de un template crud(Create,Read,Update and Delete)
export const HORAS_PROFESOR_DEF: CrudDef = { 
    name: 'HORAS_PROFESOR',
    i18n: HORAS_PROFESOR_I18N_DEF,
    grid: HORAS_PROFESOR_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: HORAS_PROFESOR_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: HORAS_PROFESOR_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  HORAS_PROFESOR_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: HORAS_PROFESOR_NAV_DEF,
    security: HORAS_PROFESOR_SECURITY_DEF,
    ws: {
        key: 'HORAS_PROFESOR_CRUD_URL',
        url: 'http://localhost:8080/ws-rest-educacion/horariosdisponibles/'
        //url: PREFIX_DOMAIN_API + 'ws-rest-educacion/horariosdisponibles/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
