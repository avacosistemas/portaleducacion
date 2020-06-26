// buscar definicion en archivo es.ts

import { environment } from 'environments/environment';
export const navigation = [
    {
        'id'      : 'administration',
        'title'   : 'Menú General',
        'translate': 'NAV.ADMINISTRATION.TITLE',
        'type'    : 'group',
        'children': [
            {
                'title' : 'Seguridad',
                'translate': 'NAV.SEGURIDAD.TITLE',
                'type' : 'collapse',
                'icon' : 'settings',
                'children' : [
                    {
                        'id'   : 'parameters',
                        'title': 'Parametros',
                        'translate': 'NAV.PARAMETERS.TITLE',
                        'type' : 'item',
                        'url'  : '/' + environment.URL_PARAMETERS,
                    },
                    {
                        'id'   : 'usuarios',
                        'title': 'Usuarios',
                        'translate': 'NAV.USUARIOS.TITLE',
                        'type' : 'item',
                        'url'  : '/' + environment.URL_PARAMETERS,
                    },
                ]
            },
            {
                'id'   : 'clases',
                'title': 'Clases',
                'translate': 'NAV.CLASES.TITLE',
                'type' : 'item',
                'url'  : '/' + environment.URL_PARAMETERS,
            },
            {
                'id'   : 'profesores',
                'title': 'Profesores',
                'translate': 'NAV.PROFESORES.TITLE',
                'type' : 'item',
                'url'  : '/' + environment.URL_PARAMETERS,
            },
            {
                'id'   : 'alumnos',
                'title': 'Alumnos',
                'translate': 'NAV.ALUMNOS.TITLE',
                'type' : 'item',
                'url'  : '/' + environment.URL_PARAMETERS,
            }
            
        ]
    }
];
