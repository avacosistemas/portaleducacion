import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const AULAS_CREATE_FORM_FIELDS_DEF = [
  // {
  //   key: 'materia',
  //   labelKey: 'aulas_create_form_fields_def_field_nombremateria',
  //   controlType: 'autocomplete',
  //   label: 'Materia',
  //   required: true,
  //   options: {
  //     transferIdToField: 'idMateria',
  //     elementLabel: 'descripcionNivel',
  //     elementValue: 'id',
  //     useNativeFilter: false,
  //     selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
  //   },
  //   apiOptions: {
  //     queryString: {
  //       descripcion: 'materia'
  //     },
  //     url: PREFIX_DOMAIN_API_EDUCACION + '/materias/'
  //   }
  // },
  // { 
  //   key: 'idMateria',   
  //   controlType: 'hidden'
  // },

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
      dependencyKey: 'idMateria'
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
    maxValue: 23,
    minValue: 0
  }
];
