import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const ALUMNO_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'alumno_grid_def_column_id'
    },
    {
      columnDef: 'nombre',
      columnNameKey: 'alumno_grid_def_column_nombre'
    },
    {
      columnDef: 'apellido',
      columnNameKey: 'alumno_grid_def_column_apellido'
    },
    {
      columnDef: 'idInstitucion',
      columnNameKey: 'alumno_grid_def_column_idinstitucion'
    },
    {
      columnDef: 'username',
      columnNameKey: 'alumno_grid_def_column_username'
    },
    {
      columnDef: 'tipoIdentificacion',
      columnNameKey: 'alumno_grid_def_column_tipoidentificacion'
    },
    {
      columnDef: 'numeroIdentificacion',
      columnNameKey: 'alumno_grid_def_column_numeroidentificacion'
    },
    {
      columnDef: 'telefonoMovil',
      columnNameKey: 'alumno_grid_def_column_telefonomovil'
    },
    {
      columnDef: 'email',
      columnNameKey: 'alumno_grid_def_column_email'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'nombre',
    'apellido',
    'idInstitucion',
    'username',
    'tipoIdentificacion',
    'numeroIdentificacion',
    'telefonoMovil',
    'email'
  ],
  actions: [
    {
      actionNameKey: 'alumno_grid_def_button_action_habilitar',
      icon: 'thumb_up',
      ws: {
        key: 'alumno_grid_def_button_action_habilitar',
        url: PREFIX_DOMAIN_API_EDUCACION + '/alumnos/habilitar/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    },

    {
      actionNameKey: 'alumno_grid_def_button_action_bloquear',
      icon: 'thumb_down',
      ws: {
        key: 'alumno_grid_def_button_action_bloquear',
        url: PREFIX_DOMAIN_API_EDUCACION + '/alumnos/bloquear/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    }

  ]
};
