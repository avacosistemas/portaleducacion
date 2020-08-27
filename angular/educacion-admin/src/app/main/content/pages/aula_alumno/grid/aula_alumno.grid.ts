export const AULA_ALUMNO_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'aula_alumno_grid_def_column_id'
    },
    {
      columnDef: 'idAula',
      columnNameKey: 'aula_alumno_grid_def_column_idaula'
    },
    {
      columnDef: 'idAlumno',
      columnNameKey: 'aula_alumno_grid_def_column_idalumno'
    },
    {
      columnDef: 'nombreAlumno',
      columnNameKey: 'aula_alumno_grid_def_column_nombrealumno'
    },
    {
      columnDef: 'calificacion',
      columnNameKey: 'aula_alumno_grid_def_column_calificacion'
    },
    {
      columnDef: 'comentario',
      columnNameKey: 'aula_alumno_grid_def_column_comentario'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'nombreAlumno',
    'calificacion',
    'comentario'
  ]
};
