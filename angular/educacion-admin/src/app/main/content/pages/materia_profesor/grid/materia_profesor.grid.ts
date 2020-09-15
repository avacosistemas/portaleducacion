export const MATERIA_PROFESOR_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'materia_profesor_grid_def_column_id',
      id: true
    },
    {
      columnDef: 'idMateria',
      columnNameKey: 'materia_profesor_grid_def_column_idmateria'
    },
    {
      columnDef: 'descMateria',
      columnNameKey: 'materia_profesor_grid_def_column_descmateria'
    },
    {
      columnDef: 'idNivel',
      columnNameKey: 'materia_profesor_grid_def_column_idnivel'
    },
    {
      columnDef: 'descNivel',
      columnNameKey: 'materia_profesor_grid_def_column_descnivel'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'descNivel',
    'descMateria'
  ],
  deleteAction: true,
  deleteTernaria: true,
  columnsTernaria: [
    'id', // IdProfesor
    'idMateria'
  ]
};
