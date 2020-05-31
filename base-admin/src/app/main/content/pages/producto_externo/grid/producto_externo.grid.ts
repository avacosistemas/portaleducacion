import { PREFIX_DOMAIN_API } from "environments/environment";

export const PRODUCTO_EXTERNO_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      id: true,
      columnNameKey: 'producto_externo_grid_def_column_id'
    },
    {
      columnDef: 'title',
      columnNameKey: 'producto_externo_grid_def_column_title'
    },
    {
      columnDef: 'header',
      columnNameKey: 'producto_externo_grid_def_column_header'
    },
    {
      columnDef: 'description',
      columnNameKey: 'producto_externo_grid_def_column_description'
    },
    {
      columnDef: 'url',
      columnNameKey: 'producto_externo_grid_def_column_url'
    },
    {
      columnDef: 'color',
      columnNameKey: 'producto_externo_grid_def_column_color'
    },
    {
      columnDef: 'imageURL',
      columnNameKey: 'producto_externo_grid_def_column_imageurl'
    },
    {
      columnDef: 'orden',
      columnNameKey: 'producto_externo_grid_def_column_orden'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'title',
    'orden'
 ],
 actions: [
  {
    actionNameKey: 'prodcuto_externo_grid_def_button_action_bajar',
    icon: 'arrow_downward',
    ws: {
      key: 'prodcuto_externo_grid_def_button_action_bajar',
      url: PREFIX_DOMAIN_API + 'ExternalProduct/BajarOrden',
      method: 'GET',
      querystring: {
        id : 'id'
      }
    }
  },
  {
    actionNameKey: 'prodcuto_externo_grid_def_button_action_subir',
    icon: 'arrow_upward',
    ws: {
      key: 'prodcuto_externo_grid_def_button_action_subir',
      url: PREFIX_DOMAIN_API + 'ExternalProduct/SubirOrden',
      method: 'GET',
      querystring: {
        id : 'id'
      }
    }
  }
]

 
};
