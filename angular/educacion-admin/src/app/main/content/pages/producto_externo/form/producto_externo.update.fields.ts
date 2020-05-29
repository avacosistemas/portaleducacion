export const PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF = [
  {
    key: 'id',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_id',
    label: 'ID',
    type: 'hidden',
    controlType: 'hidden'
  },
  {
    key: 'title',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_title',
    label: 'Titulo',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100
  },
  {
    key: 'tooltip',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_tooltip',
    label: 'Tooltip',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100
  },
  {
    key: 'header',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_header',
    label: 'Header',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100
  },
  {
    key: 'url',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_url',
    label: 'URL',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 1000
  },
  {
    key: 'description',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_description',
    label: 'Descripci�n',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 8000
  },
  {
    key: 'color',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_color',
    label: 'Color',
    controlType: 'color_picker',
    required: true,
    maxLength: 100
  },
  {
    key: 'textColor',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_textcolor',
    controlType: 'color_picker',
    required: true,
    maxLength: 100
  },
  {
    key: 'imageURL',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_imageurl',
    label: 'Imagen',
    controlType: 'import_image',
    required: true,
    maxLength: 100,
    showPreview: true
  },
  {
    key: 'orden',
    labelKey: 'PRODUCTO_EXTERNO_UPDATE_FORM_FIELDS_DEF_FIELD_orden',
    label: 'Orden',
    type: 'number',
    controlType: 'number',
    required: true
  }
];
