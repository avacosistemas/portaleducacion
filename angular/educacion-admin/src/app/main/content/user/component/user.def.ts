import { USER_SEARCH_FIELDS } from '../form/search.fields';
import { PREFIX_DOMAIN_API, environment } from 'environments/environment';
import { CrudDef } from 'app/modules/fwk/core/model/component-def/crud-def';
import { REGEX_KEY_USER } from 'app/modules/fwk/core/service/dynamic-form/form.validator.service';
const PROFILES_COMPONENT_KEY = 'profiles';
// Definicion de un template crud(Create,Read,Update and Delete)
export const USER_DEF: CrudDef = { 

    name : 'user',
    dialogConfig: {
      width: 'auto'
    },
    i18n: {
        name: 'user',
        lang: 'es',
        words: {
          page_title: 'Usuarios',
          user_nav_title: 'Usuarios'
        }
    },
    navigation: {
        id: 'user',
        translateKey: 'user_nav_title',
        url: '/' + environment.URL_USER
    },

    security: {
        createAccess: 'USUARIO_CREATE',
        deleteAccess: 'USUARIO_DELETE',
        readAccess: 'USUARIO_READ',
        updateAccess: 'USUARIO_MODIFY',
    },

    
    grid: {
            columnsDef: [
                          {columnNameKey: 'Id', columnDef: 'id'},
                          {columnNameKey: 'Nombre de Usuario', columnDef: 'username'},
                          {columnNameKey: 'Nombre', columnDef: 'name'},
                          {columnNameKey: 'Apellido', columnDef: 'lastname'},
                          {columnNameKey: 'Email', columnDef: 'email'}
                        ],
            sortAllColumns: true,
            displayedColumns: ['username', 'name', 'lastname', 'email'],
            deleteColumn: { key: 'delete',
                            name: 'Eliminar'}
            },
    forms: {
        validationAddURL: environment.USER_VALIDATION_ADD_URL,
        validationEditURL: environment.USER_VALIDATION_EDIT_URL,
        filter: USER_SEARCH_FIELDS,
        create: [{               
              key: 'username',
              labelKey: 'Nombre de Usuario',
              required: true,
              requiredMessage: 'El campo nombre de usuario es requerido',
              controlType: 'textbox',
              minLength: 5,
              maxLength: 50,
              validation: {
                regexKey: 'user'
              }
            },
            {
              key: 'name',
              labelKey: 'Nombre',
              requiredMessage: 'El campo nombre es requerido',
              controlType: 'textbox',
              maxLength: 100
            },
            {
              key: 'lastname',
              labelKey: 'Apellido',
              requiredMessage: 'El campo apellido es requerido',
              controlType: 'textbox',
              maxLength: 100
            },
            {
              key: 'email',
              labelKey: 'Email',
              required: true,
              requiredMessage: 'El campo email es requerido',
              controlType: 'email',
              maxLength: 100
            },
            {
              key: PROFILES_COMPONENT_KEY,
              labelKey: 'Perfiles',
              required: true,
              requiredMessage: 'El campo perfil es requerido',
              controlType: 'pick-list',
              options: {
                  compositeKey: ['id'],
                  elementLabel: 'name',
                  titleFrom: 'Perfiles',
                  titleTo: 'Perfiles cargados',
                  fromWs: {
                    key: 'user_ws_profiles',
                    url: environment.PROFILE_CRUD_URL
                  },
              }
            }],
        update:  [{               
                  key: 'username',
                  labelKey: 'Nombre de Usuario',
                  required: true,
                  disabled: true,
                  requiredMessage: 'El campo nombre de usuario es requerido',
                  controlType: 'textbox',
                  maxLength: 50,
                  validation: {
                    regexKey: REGEX_KEY_USER
                  }
                },
                {
                  key: 'name',
                  labelKey: 'Nombre',
                  requiredMessage: 'El campo nombre es requerido',
                  controlType: 'textbox',
                  maxLength: 100
                },
                {
                  key: 'lastname',
                  labelKey: 'Apellido',
                  requiredMessage: 'El campo apellido es requerido',
                  controlType: 'textbox',
                  maxLength: 100
                },
                {
                  key: 'email',
                  labelKey: 'Email',
                  required: true,
                  requiredMessage: 'El campo email es requerido',
                  controlType: 'email',
                  maxLength: 100
                },
                {
                  key: 'enabled',
                  labelKey: 'Habilitado',
                  controlType: 'checkbox',
                },
                {
                  key: PROFILES_COMPONENT_KEY,
                  labelKey: 'Perfiles',
                  required: true,
                  requiredMessage: 'El campo perfil es requerido',
                  controlType: 'pick-list',
                  options: {
                      compositeKey: ['id'],
                      elementLabel: 'name',
                      titleFrom: 'Perfiles',
                      titleTo: 'Perfiles cargados',
                      fromWs: {
                        key: 'user_ws_profiles',
                        url: environment.PROFILE_CRUD_URL
                      }
                  }
                }],
        read:  [],
    },  
    ws: {
        key: 'USER_CRUD_URL',
        url: environment.USER_CRUD_URL
    },
    test: [{
      url: PREFIX_DOMAIN_API + 'ws-rest-authentication/user',
      dataset: [{
        authorities: [
          {authority: 'PERFIL_CREATE'},
          {authority: 'PERFIL_READ'},
          {authority: 'PERFIL_UPDATE'},
          {authority: 'PERFIL_DELETE'}
        ]
      }]

    }]

};
