import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const MATERIAS_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'idNivel',
    labelKey: 'materias_create_form_fields_def_field_idnivel',
    required: true,
    controlType: 'select',
    options: {
        elementLabel: 'descripcion',
        elementValue: 'id',
        fromWs: {
          key: 'materias_create_form_fields_def_field_idnivel',
          url: PREFIX_DOMAIN_API_EDUCACION + '/niveles/'
      }
    }
  },
  {
    key: 'descripcion',
    labelKey: 'materias_create_form_fields_def_field_descripcion',
    label: 'Descripcion',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLenght: 100
  }
];
