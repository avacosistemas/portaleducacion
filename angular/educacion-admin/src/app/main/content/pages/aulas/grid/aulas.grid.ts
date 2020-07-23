export const AULAS_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'aulas_grid_def_column_id'
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
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'nombreMateria',
    'nombreInstitucion',
    'dia',
    'hora'
  ],
  actions: [
    {
      actionNameKey: 'aulas_grid_def_button_action_alumnos',
      actionType: 'redirect',
      redirect: {
        url: '/aulaAlumnos',
        querystring: {
          idProfesor : 'id',
          parentTitle: 'nombreApellido'
        }
      },
      icon: 'library_books'
    }
  ]
};
