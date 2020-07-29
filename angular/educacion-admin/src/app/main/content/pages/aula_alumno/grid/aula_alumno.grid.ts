export const AULA_ALUMNO_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'aula_alumno_grid_def_column_id'
    },
    {
      columnDef: 'idAlumno',
      columnNameKey: 'aula_alumno_grid_def_column_idalumno'
    },
    {
      columnDef: 'idAula',
      columnNameKey: 'aula_alumno_grid_def_column_idaula'
    },
    {
      columnDef: 'nombreAlumno',
      columnNameKey: 'aula_alumno_grid_def_column_nombrealumno'
    },
    {
      columnDef: 'idInstitucion',
      columnNameKey: 'aula_alumno_grid_def_column_idinstitucion'
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
  ],
  deleteAction: true,
};
