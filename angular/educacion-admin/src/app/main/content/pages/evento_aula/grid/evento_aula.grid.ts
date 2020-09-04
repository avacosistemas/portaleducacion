export const EVENTO_AULA_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'fecha',
      columnNameKey: 'evento_aula_grid_def_column_fecha'
    },
    {
      columnDef: 'usuario',
      columnNameKey: 'evento_aula_grid_def_column_usuario'
    },
    {
      columnDef: 'tipoUsuario',
      columnNameKey: 'evento_aula_grid_def_column_tipousuario'
    },
    {
      columnDef: 'idAula',
      columnNameKey: 'evento_aula_grid_def_column_idaula'
    },
    {
      columnDef: 'tipoEvento',
      columnNameKey: 'evento_aula_grid_def_column_tipoevento'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'fecha',
    'tipoEvento',
    'usuario',
    'tipoUsuario'
  ]
};
