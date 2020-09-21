import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const AULAS_UPDATE_FORM_FIELDS_DEF = [
  {
    key: 'id',
    labelKey: 'AULAS_UPDATE_FORM_FIELDS_DEF_FIELD_id',
    label: 'ID',
    type: 'string',
    controlType: 'hidden'
  },
  

  {
    key: 'idProfesor',
    labelKey: 'aulas_create_form_fields_def_field_nombreProfesor',
    required: true,
    controlType: 'select',
    options: {
        elementLabel: 'nombreApellido',
        elementValue: 'id',
        fromWs: {
          key: 'alumno_create_form_fields_def_field_idinstitucion',
          url: PREFIX_DOMAIN_API_EDUCACION + '/profesores/'
        }
    },
    dependencies: {
      parentDependencyKey: 'idMateria',
    }
  },

  {
    key: 'idMateria',
    labelKey: 'aulas_create_form_fields_def_field_nombremateria',
    required: true,
    controlType: 'select',
    options: {
        elementLabel: 'descMateria',
        elementValue: 'idMateria',
        fromWs: {
          key: 'alumno_create_form_fields_def_field_idinstitucion',
          url: PREFIX_DOMAIN_API_EDUCACION + '/materiasprofesor/'
        }
    },
    dependencies: {
      parentDependencyKey: 'idProfesor',
      stopLoad: true
    }
  },

  {
    key: 'idInstitucion',
    labelKey: 'aulas_create_form_fields_def_field_nombreinstitucion',
    required: false,
    controlType: 'select',
    options: {
        elementLabel: 'nombre',
        elementValue: 'id',
        fromWs: {
          key: 'alumno_create_form_fields_def_field_idinstitucion',
          url: PREFIX_DOMAIN_API_EDUCACION + '/instituciones'
        }
    }
  },
  {
    key: 'dia',
    labelKey: 'aulas_create_form_fields_def_field_fecha',
    label: 'Fecha',
    type: 'date',
    controlType: 'datepicker',
    required: true
  },
  {
    key: 'hora',
    labelKey: 'aulas_create_form_fields_def_field_hora',
    label: 'Hora',
    type: 'string',
    controlType: 'number',
    required: true,
    minValue: 0,
    maxValue: 23
  }
];
