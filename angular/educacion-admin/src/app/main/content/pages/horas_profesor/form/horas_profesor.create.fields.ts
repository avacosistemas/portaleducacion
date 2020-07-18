import { PREFIX_DOMAIN_API } from "environments/environment";

export const HORAS_PROFESOR_CREATE_FORM_FIELDS_DEF = [
  /*
  {
    key: 'id',
    labelKey: 'horas_profesor_create_form_fields_def_field_id',
    label: 'ID',
    type: 'number',
    controlType: 'number'
  },
  */
  {
    key: 'dia',
    labelKey: 'horas_profesor_create_form_fields_def_field_dia',
    label: 'Día',
    type: 'string',
    controlType: 'textbox'
  },
  {
    key: 'hora',
    labelKey: 'horas_profesor_create_form_fields_def_field_hora',
    label: 'Hora',
    type: 'string',
    controlType: 'textbox'
  }
];
