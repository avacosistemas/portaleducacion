import { PERMISSION_SEARCH_FIELDS } from '../form/search.fields';
import { environment } from 'environments/environment';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';

// Definicion de un template crud(Create,Read,Update and Delete)
export const PERMISSION_DEF: CrudDef = { 

    name : 'permission',
    i18n: {
        name: 'permission',
        lang: 'es',
        words: {
             page_title: 'Permisos',
             permission_nav_title: 'Permisos'
        }
    },
    navigation: {
        id: 'permission',
        translateKey: 'permission_nav_title',
        url: '/' + environment.URL_PERMISSION
    },

    security: {        
        createAccess: 'PERMISOS_CREATE',
        deleteAccess: 'PERMISOS_DELETE',
        readAccess: 'PERMISOS_READ',
        updateAccess: 'PERMISOS_MODIFY'
    },

    
    grid: {
            columnsDef: [
                        {columnNameKey: 'Id', columnDef: 'id'},
                        {columnNameKey: 'Código', columnDef: 'code'},
                        {columnNameKey: 'Descripción', columnDef: 'description'},
                        {columnNameKey: 'Activo', columnDef: 'enabled'}
                        ],
            sortAllColumns: true,
            displayedColumns: ['code', 'description', 'enabled']
    },
    forms: {
        filter: PERMISSION_SEARCH_FIELDS,
        read: [{               
            key: 'code',
            labelKey: 'Código',
            
            controlType: 'textbox'
            },
            {
            key: 'description',
            labelKey: 'Descripción',
            
            controlType: 'textbox'
            },
            {
            key: 'enabled',
            labelKey: 'Activo',
            
            controlType: 'checkbox'
            }]
    },
    ws: {
        key: 'PERMISSION_CRUD_URL',
        url: environment.PERMISSION_CRUD_URL
    }
};
