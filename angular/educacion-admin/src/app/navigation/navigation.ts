import { environment } from 'environments/environment';
export const navigation = [
    {
        'id'      : 'administration',
        'title'   : 'Menú General',
        'translate': 'NAV.ADMINISTRATION',
        'type'    : 'group',
        'children': [
           
            {
                'title' : 'Gestión de Contenido',
                'translate': 'NAV.GESTION_CONTENIDOS',
                'type' : 'collapse',
                'icon' : 'settings',
                'children' : [
                    {
                        'title' : 'Contenidos',
                        'translate': 'NAV.MENU_CONTENIDOS',
                        'type' : 'collapse',
                        'icon' : 'settings',
                        'children' : [
                            {
                                'id'   : 'seccion',
                                'title': 'Secciones',
                                'translate': 'NAV.SECCION.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_SECCIONES,
                            },
                            {
                                'id'   : 'contenido',
                                'title': 'Contenidos',
                                'translate': 'NAV.CONTENIDO.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_CONTENIDOS,
                            },
                            {
                                'id'   : 'imagen',
                                'title': 'Imágenes',
                                'translate': 'NAV.IMAGEN.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_IMAGEN,
                            },
                            {
                                'id'   : 'productoExterno',
                                'title': 'Productos',
                                'translate': 'NAV.PRODUCTOEXTERNO.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_PRODUCTO_EXTERNO,
                            },
                        ]
                    },
                    {
                        'title' : 'Newsletter',
                        'translate': 'NAV.MENU_BOLETINES',
                        'type' : 'collapse',
                        'icon' : 'settings',
                        'children' : [
                            {
                                'id'   : 'boletin',
                                'title': 'Boletínes',
                                'translate': 'NAV.BOLETIN.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_BOLETIN,
                            },
                            {
                                'id'   : 'indice',
                                'title': 'Índices',
                                'translate': 'NAV.INDICE.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_INDICE,
                            },
                            {
                                'id'   : 'mailing',
                                'title': 'Mailing',
                                'translate': 'NAV.MAILING.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_MAILING,
                            },
                        ]
                    },
                    {
                        'title' : 'Herramientas',
                        'translate': 'NAV.MENU_HERRAMIENTAS',
                        'type' : 'collapse',
                        'icon' : 'settings',
                        'children' : [
                            {
                                'id'   : 'formulario',
                                'title': 'Formularios',
                                'translate': 'NAV.FORMULARIO.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_FORMULARIO,
                            },
                            {
                                'id'   : 'publicidad',
                                'title': 'Publicidades',
                                'translate': 'NAV.PUBLICIDAD.TITLE',
                                'type' : 'item',
                                'url'  : '/' + environment.URL_PUBLICIDAD,
                            },
                        ]
                    },
                ]
            },
            
        ]
    }
];
