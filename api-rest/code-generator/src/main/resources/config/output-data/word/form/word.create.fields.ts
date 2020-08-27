export const WORD_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'key',
    labelKey: 'word_create_form_fields_def_field_key',
    label: 'Clave',
    type: 'string',
    required: true,
    controlType: 'textbox',
    maxLength: 70
  },
  {
    key: 'value',
    labelKey: 'word_create_form_fields_def_field_value',
    label: 'Valor',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  }
];
