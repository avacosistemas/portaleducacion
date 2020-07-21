export const MATERIAS_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      id:true,
      columnNameKey: 'materias_grid_def_column_id'
    },
    {
      columnDef: 'idNivel',
      columnNameKey: 'materias_grid_def_column_idnivel'
    },
    {
      columnDef: 'nivel',
      columnNameKey: 'materias_grid_def_column_idnivel'
    },
    {
      columnDef: 'descripcion',
      columnNameKey: 'materias_grid_def_column_descripcion'
    },
    {
      columnDef: 'descripcionNivel',
      columnNameKey: 'materias_grid_def_column_nivel_descripcion'
    }
  ],
  sortAllColumns: true,
  deleteAction: true, 
  displayedColumns: [
    'nivel',
    'descripcion'
  ]
};
