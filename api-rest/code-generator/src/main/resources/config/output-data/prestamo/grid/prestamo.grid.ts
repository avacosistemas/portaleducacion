export const PRESTAMO_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'idPrestamo',
      columnNameKey: 'prestamo_grid_def_column_idprestamo'
    },
    {
      columnDef: 'importeSolicitado',
      columnNameKey: 'prestamo_grid_def_column_importesolicitado'
    },
    {
      columnDef: 'cuotas',
      columnNameKey: 'prestamo_grid_def_column_cuotas'
    },
    {
      columnDef: 'estado',
      columnNameKey: 'prestamo_grid_def_column_estado'
    },
    {
      columnDef: 'nombreCliente',
      columnNameKey: 'prestamo_grid_def_column_nombrecliente'
    },
    {
      columnDef: 'tasa',
      columnNameKey: 'prestamo_grid_def_column_tasa'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'idPrestamo',
    'importeSolicitado',
    'cuotas',
    'estado',
    'nombreCliente',
    'tasa'
  ]
};
