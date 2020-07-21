
export const INSTITUCIONES_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      id:true,
      columnNameKey: 'instituciones_grid_def_column_id'
    },
    {
      columnDef: 'nombre',
      columnNameKey: 'instituciones_grid_def_column_nombre'
    }
  ],
  sortAllColumns: true,
  deleteAction: true, 

  
  displayedColumns: [
    'nombre'
  ]
};
