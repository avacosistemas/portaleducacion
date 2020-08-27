export const HORAS_PROFESOR_GRID_DEF = {
  columnsDef: [
    
    {
      columnDef: 'id',
      columnNameKey: 'horas_profesor_grid_def_column_id',
      id: true
    },
    {
      columnDef: 'dia',
      columnNameKey: 'horas_profesor_grid_def_column_dia'
    },
    {
      columnDef: 'hora',
      columnNameKey: 'horas_profesor_grid_def_column_hora'
    },
    {
      columnDef: 'rangoHora',
      columnNameKey: 'horas_profesor_grid_def_column_hora'
    }
    
  ],
  sortAllColumns: true,
  displayedColumns: [        
    'dia',
    'rangoHora'
  ],
  deleteAction: true
};
