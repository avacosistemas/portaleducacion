import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const AULA_ALUMNO_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'idAula',
    label: 'idAula',
    controlType: 'hidden',
    mappingQuerystring: true
  },
  {
    key: 'idInstitucion',
    label: 'idInstitucion',
    controlType: 'hidden',
    mappingQuerystring: true
  },
  {
    key: 'idAlumno',
    labelKey: 'aula_alumno_create_form_fields_def_field_alumno',
    controlType: 'select',
    required: true,
    options: {
      elementLabel: 'nombreApellido',
      elementValue: 'id',
      fromWs: {
        key: 'alumno_create_form_fields_def_field_alumno',
        url: PREFIX_DOMAIN_API_EDUCACION + '/alumnos',
        querystring: {
          idInstitucion : 'idInstitucion',
        }
      }
    }
  },
];
