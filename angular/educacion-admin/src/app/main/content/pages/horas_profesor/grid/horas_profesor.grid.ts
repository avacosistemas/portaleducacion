export const HORAS_PROFESOR_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'horas_profesor_grid_def_column_id'
    },
    {
      columnDef: 'dia',
      columnNameKey: 'horas_profesor_grid_def_column_dia'
    },
    {
      columnDef: 'hora',
      columnNameKey: 'horas_profesor_grid_def_column_hora'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [    
    'dia',
    'hora'
  ],
  deleteAction: true
};
