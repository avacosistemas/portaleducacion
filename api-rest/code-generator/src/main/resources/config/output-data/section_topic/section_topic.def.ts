import { SECTION_TOPIC_CREATE_FORM_FIELDS_DEF } from './form/section_topic.create.fields';
import { SECTION_TOPIC_UPDATE_FORM_FIELDS_DEF } from './form/section_topic.update.fields';
import { SECTION_TOPIC_READ_FORM_FIELDS_DEF } from './form/section_topic.read.fields';
import { SECTION_TOPIC_FILTER_FORM_FIELDS_DEF } from './form/section_topic.filter.fields';
import { SECTION_TOPIC_SECURITY_DEF } from './security/section_topic.security';
import { SECTION_TOPIC_GRID_DEF } from './grid/section_topic.grid';
import { SECTION_TOPIC_I18N_DEF } from './i18n/section_topic.i18n';
import { SECTION_TOPIC_NAV_DEF } from './navigation/section_topic.nav';

// Definicion de un template crud(Create,Read,Update and Delete)
export const SECTION_TOPIC_DEF: CrudDef = { 
    name: 'SECTION_TOPIC',
    i18n: SECTION_TOPIC_I18N_DEF,
    grid: SECTION_TOPIC_GRID_DEF, // Si el crud tiene grilla, entonces se agrega su definicion.
    forms: {
        filter: SECTION_TOPIC_FILTER_FORM_FIELDS_DEF, // Si el crud tiene campos de busqueda, entonces se agrega su definicion.
        create: SECTION_TOPIC_CREATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de alta, entonces se agrega su definicion.
        update: SECTION_TOPIC_UPDATE_FORM_FIELDS_DEF, // Si el crud tiene formulario de modificacion, entonces se agrega su definicion.
        read:  SECTION_TOPIC_READ_FORM_FIELDS_DEF // Si existe un formulario de edicion no exite uno de solo lectura
    },
    navigation: SECTION_TOPIC_NAV_DEF,
    security: SECTION_TOPIC_SECURITY_DEF,
    ws: {
        key: 'SECTION_TOPIC_CRUD_URL',
        url: 'http://localhost:5000/api/SectionTopic/'
    },
    dialogConfig: {
        width: '400px'
    }   
};
