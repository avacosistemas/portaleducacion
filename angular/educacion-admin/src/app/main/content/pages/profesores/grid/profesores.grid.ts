import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const PROFESORES_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'profesores_grid_def_column_id'
    },
    {
      columnDef: 'nombreApellido',
      columnNameKey: 'profesores_grid_def_column_nombreApellido'
    },
    {
      columnDef: 'nombre',
      columnNameKey: 'profesores_grid_def_column_nombre'
    },
    {
      columnDef: 'apellido',
      columnNameKey: 'profesores_grid_def_column_apellido'
    },
    {
      columnDef: 'tipoIdentificacion',
      columnNameKey: 'profesores_grid_def_column_tipoidentificacion'
    },
    {
      columnDef: 'numeroIdentificacion',
      columnNameKey: 'profesores_grid_def_column_numeroidentificacion'
    },
    {
      columnDef: 'username',
      columnNameKey: 'profesores_grid_def_column_username'
    },
    {
      columnDef: 'email',
      columnNameKey: 'profesores_grid_def_column_email'
    },
    {
      columnDef: 'telefonoMovil',
      columnNameKey: 'profesores_grid_def_column_telefonomovil'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'nombreApellido',
    'nombre',
    'apellido',
    'tipoIdentificacion',
    'numeroIdentificacion',
    'username',
    'email',
    'telefonoMovil'
  ],
  actions: [
    {
      actionNameKey: 'profesores_grid_def_button_action_materia_profesor',
      actionType: 'redirect',
      redirect: {
        url: '/materiaProfesor',
        querystring: {
          idProfesor : 'id',
          parentTitle: 'nombreApellido'
        }
      },
      icon: 'library_books'
    },

    {
      actionNameKey: 'profesores_grid_def_button_action_nuevo_horario',
      actionType: 'redirect',
      redirect: {
        url: '/horasProfersor',
        querystring: {
          id : 'id',
          parentTitle: 'nombreApellido'
        }
      },
      icon: 'more_time'
    },
   
    
    {
      actionNameKey: 'profesores_grid_def_button_action_habilitar',
      icon: 'thumb_up',
      ws: {
        key: 'profesores_grid_def_button_action_habilitar',
        url: PREFIX_DOMAIN_API_EDUCACION + '/profesores/habilitar/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    },

    {
      actionNameKey: 'profesores_grid_def_button_action_bloquear',
      icon: 'thumb_down',
      ws: {
        key: 'profesores_grid_def_button_action_bloquear',
        url: PREFIX_DOMAIN_API_EDUCACION + '/profesores/bloquear/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    }

  ]
};
