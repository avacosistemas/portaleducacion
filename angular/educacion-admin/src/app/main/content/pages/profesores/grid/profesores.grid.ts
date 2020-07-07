export const PROFESORES_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'profesores_grid_def_column_id'
    },
    {
      columnDef: 'nombreApellido',
      columnNameKey: 'profesores_grid_def_column_nombreapellido'
    },
    {
      columnDef: 'tipoIdentificacion',
      columnNameKey: 'profesores_grid_def_column_tipoidentificacion'
    },
    {
      columnDef: 'numeroIdentificacion',
      columnNameKey: 'profesores_grid_def_column_numeroidentificacion'
    },
    {
      columnDef: 'username',
      columnNameKey: 'profesores_grid_def_column_username'
    },
    {
      columnDef: 'email',
      columnNameKey: 'profesores_grid_def_column_email'
    },
    {
      columnDef: 'telefonoMovil',
      columnNameKey: 'profesores_grid_def_column_telefonomovil'
    }
  ],
  sortAllColumns: true,
  deleteAction: true, 
  displayedColumns: [
    'id',
    'nombreApellido',
    'tipoIdentificacion',
    'numeroIdentificacion',
    'username',
    'email',
    'telefonoMovil'
  ]
};
