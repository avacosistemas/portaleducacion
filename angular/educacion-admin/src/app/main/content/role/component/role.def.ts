import { environment } from 'environments/environment';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';

// Definicion de un template crud(Create,Read,Update and Delete)
export const ROLE_DEF: CrudDef = { 

    name : 'role',
    i18n: {
        name: 'role',
        lang: 'es',
        words: {
          page_title: 'Roles',
          role_nav_title: 'Roles'
        }
    },

    navigation: {
        id: 'role_nav',
        translateKey: 'role_nav_title',
        url: '/' + environment.URL_ROLE
    },

    security: {               
        createAccess: 'ROLE_CREATE',
        deleteAccess: 'ROLE_DELETE',
        readAccess: 'ROLE_READ',
        updateAccess: 'ROLE_MODIFY'
    },

    grid: {
            columnsDef: [
                          {columnNameKey: 'Id', columnDef: 'id'},
                          {columnNameKey: 'Código', columnDef: 'code'},
                          {columnNameKey: 'Nombre', columnDef: 'name'}
                        ],
            displayedColumns: ['delete', 'id', 'code', 'name'],
            deleteColumn: { key: 'delete',
                            name: 'Eliminar'}
            },
    forms: {
        create: [{               
                key: 'code',
                labelKey: 'Código',
                required: true,
                requiredMessage: 'El campo código es requerido',
                
                controlType: 'textbox',
                maxLength: 10
                }, {
                key: 'name',
                labelKey: 'Nombre',
                required: true,
                requiredMessage: 'El campo nombre es requerido',
                
                controlType: 'textbox',
                maxLength: 50
            }],
        update:  [{               
                key: 'code',
                labelKey: 'Código',
                required: true,
                requiredMessage: 'El campo código es requerido',
                
                controlType: 'textbox',
                maxLength: 10
                }, {
                key: 'name',
                labelKey: 'Nombre',
                required: true,
                requiredMessage: 'El campo nombre es requerido',
                
                controlType: 'textbox',
                maxLength: 50
                }],
        read:  [{               
                    key: 'code',
                    labelKey: 'Código',
                    
                    controlType: 'textbox'
                },
                {
                    key: 'name',
                    labelKey: 'Nombre',
                    
                    controlType: 'textbox'
                }]
    },
    ws: {
        key: 'ROLE_CRUD_URL',
        url: environment.ROLE_CRUD_URL
    }
    
};
