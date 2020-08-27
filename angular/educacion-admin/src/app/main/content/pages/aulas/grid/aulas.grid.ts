export const AULAS_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'aulas_grid_def_column_id',
      id: true
    },
    {
      columnDef: 'idString',
      columnNameKey: 'aulas_grid_def_column_id',
    },
    {
      columnDef: 'idProfesor',
      columnNameKey: 'aulas_grid_def_column_idprofesor'
    },
    {
      columnDef: 'nombreProfesor',
      columnNameKey: 'aulas_grid_def_column_nombreProfesor'
    },
    {
      columnDef: 'idMateria',
      columnNameKey: 'aulas_grid_def_column_idmateria'
    },
    {
      columnDef: 'nombreMateria',
      columnNameKey: 'aulas_grid_def_column_nombremateria'
    },
    {
      columnDef: 'idInstitucion',
      columnNameKey: 'aulas_grid_def_column_idinstitucion'
    },
    {
      columnDef: 'nombreInstitucion',
      columnNameKey: 'aulas_grid_def_column_nombreinstitucion'
    },
    {
      columnDef: 'dia',
      columnNameKey: 'aulas_grid_def_column_fecha'
    },
    {
      columnDef: 'hora',
      columnNameKey: 'aulas_grid_def_column_hora'
    },
    {
      columnDef: 'calificacion',
      columnNameKey: 'aulas_grid_def_column_calificacion'
    },
    {
      columnDef: 'horaString',
      columnNameKey: 'aulas_grid_def_column_hora'
    },
    {
      columnDef: 'estado',
      columnNameKey: 'aulas_grid_def_column_estado'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'idString',
    'nombreProfesor',
    'nombreMateria',
    'nombreInstitucion',
    'dia',
    'horaString',
    'estado'
  ],
  actions: [
    {
      actionNameKey: 'aulas_grid_def_button_action_alumnos',
      actionType: 'redirect',
      redirect: {
        url: '/aulaAlumno',
        querystring: {
          idAula : 'id',
          idInstitucion: 'idInstitucion',
          parentTitle: 'idString'
        }
      },
      icon: 'group'
    }
  ]
};
