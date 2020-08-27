
import { PROFILE_SEARCH_FIELDS } from '../form/profile.search.fields';
import {environment, PREFIX_DOMAIN_API_EDUCACION} from 'environments/environment';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
const PERMISSIONS_COMPONENT_KEY = 'permissions';
const ROLE_COMPONENT_KEY = 'role';
// Definicion de un template crud(Create,Read,Update and Delete)
export const PROFILE_DEF: CrudDef = {

    name : 'profile',
    i18n : {
        name: 'profile',
        lang: 'es',
        words: {
          page_title: 'Perfiles',
        }
      },
    navigation: {
        id: 'profile',
        translateKey: 'profile_nav_title',
        url: '/' + environment.URL_PROFILE
    },

    security: {
        createAccess: 'PERFIL_CREATE',
        deleteAccess: 'PERFIL_DELETE',
        readAccess: 'PERFIL_READ',
        updateAccess: 'PERFIL_MODIFY',
    },

    dialogConfig: {
        width: 'auto'
    },

    grid: {
            columnsDef: [
                            {columnNameKey: 'Id', columnDef: 'id'},
                            {columnNameKey: 'Nombre', columnDef: 'name'},
                            {columnNameKey: 'Rol', columnDef: 'role.name'},
                            {columnNameKey: 'Activo', columnDef: 'enabled'}
                        ],
            sortAllColumns: true,
            displayedColumns: ['name', 'enabled'],
            deleteColumn: { key: 'delete',
                            name: 'Eliminar'}
            },
    forms: {
        filter: PROFILE_SEARCH_FIELDS,
        create: [{
                key: 'name',
                labelKey: 'Nombre',
                required: true,
                requiredMessage: 'El campo nombre es requerido',

                controlType: 'textbox',
                maxLength: 50
            },
            {
                key: 'enabled',
                labelKey: 'Activo',
                requiredMessage: 'El campo activo es requerido',

                controlType: 'checkbox'
            },
            {
                key: PERMISSIONS_COMPONENT_KEY,
                labelKey: 'Permisos',
                required: true,
                requiredMessage: 'El campo permisos es requerido',
                controlType: 'pick-list',
                options: {
                    compositeKey: ['id'],
                    elementLabel: 'code',
                    titleFrom: 'Permisos',
                    titleTo: 'Permisos cargados',
                    fromWs: {
                        key: 'user_ws_profiles',
                        url: environment.PERMISSION_CRUD_URL
                    }
                }
            }],
    update:  [{
        key: 'name',
        labelKey: 'Nombre',
        required: true,
        requiredMessage: 'El campo nombre es requerido',
        controlType: 'textbox',
        maxLength: 50
        },
        {
        key: 'enabled',
        labelKey: 'Activo',
        requiredMessage: 'El campo activo es requerido',
        controlType: 'checkbox'
        },
        {
        key: PERMISSIONS_COMPONENT_KEY,
        labelKey: 'Permisos',
        required: true,
        requiredMessage: 'El campo permisos es requerido',

        controlType: 'pick-list',
        options: {
            compositeKey: ['id'],
            elementLabel: 'code',
            titleFrom: 'Permisos',
            titleTo: 'Permisos cargados',
            fromWs: {
                key: 'user_ws_profiles',
                url: environment.PERMISSION_CRUD_URL
            }
        }
        }],
    read:  [{
            key: 'code',
            labelKey: 'Codigo',

            controlType: 'textbox'
            },
            {
            key: 'description',
            labelKey: 'Descripcion',

            controlType: 'textbox'
            },
            {
            key: 'enabled',
            labelKey: 'Activo',

            controlType: 'checkbox'
            }]
    },
    ws: {
        key: 'PROFILE_CRUD_URL',
        url: environment.PROFILE_CRUD_URL,
    }
};
