export const FAQS_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      id: true,
      columnNameKey: 'faqs_grid_def_column_id'
    },
    {
      columnDef: 'favourite',
      columnNameKey: 'faqs_grid_def_column_favourite'
    },
    {
      columnDef: 'order',
      columnNameKey: 'faqs_grid_def_column_order'
    },
    {
      columnDef: 'lang',
      columnNameKey: 'faqs_grid_def_column_lang'
    },
    {
      columnDef: 'category',
      columnNameKey: 'faqs_grid_def_column_category'
    },
    {
      columnDef: 'subcategory',
      columnNameKey: 'faqs_grid_def_column_subcategory'
    },
    {
      columnDef: 'question',
      columnNameKey: 'faqs_grid_def_column_question'
    },
    {
      columnDef: 'answer',
      columnNameKey: 'faqs_grid_def_column_answer'
    }
  ],
  sortAllColumns: true,
  deleteAction: true,
  displayedColumns: [
    'question',
    'answer'
  ]
};
