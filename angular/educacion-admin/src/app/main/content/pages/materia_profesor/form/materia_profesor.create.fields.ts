import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const MATERIA_PROFESOR_CREATE_FORM_FIELDS_DEF = [
  
 {
    key: 'idProfesor',
    labelKey: 'materia_profesor_create_form_fields_def_field_idprodfesor',
    label: 'idProfesor',
    type: 'hidden',
    mappingQuerystring: true,
    controlType: 'hidden'
  },

/*
 {
    key: 'nivel',
    labelKey: 'materia_profesor_create_form_fields_def_field_nivel',
    controlType: 'autocomplete',
    label: 'Nivel',
    required: true,
    options: {
      transferIdToField: 'idNivel',
      elementLabel: 'descripcion',
      elementValue: 'id',
      useNativeFilter: false,
      selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
    },
    apiOptions: {
      queryString: {
        descripcion: 'nivel'
      },
      url: PREFIX_DOMAIN_API + 'ws-rest-educacion/niveles/'
    }
  },
  { 
    key: 'idNivel',   
    controlType: 'hidden'
  },
*/
{
  key: 'idNivel',
  labelKey: 'materia_profesor_create_form_fields_def_field_nivel',
  required: true,
  controlType: 'select',
  options: {
      elementLabel: 'descripcion',
      elementValue: 'id',
      fromWs: {
        key: 'materia_profesor_create_form_fields_def_field_nivel',
        url: PREFIX_DOMAIN_API_EDUCACION + '/niveles/'
      }
  },
  dependencies: {
    dependencyKey: 'idMateria'
  }
},
{
  key: 'idMateria',
  labelKey: 'materia_profesor_create_form_fields_def_field_materia',
  required: true,
  controlType: 'select',
  options: {
      elementLabel: 'descripcion',
      elementValue: 'id',
      fromWs: {
        key: 'materia_profesor_create_form_fields_def_field_materia',
        url: PREFIX_DOMAIN_API_EDUCACION + '/materias/nivel/'
      }
  },
  dependencies: {
    stopLoad: true
  }
},
  // {
  //   key: 'materia',
  //   labelKey: 'materia_profesor_create_form_fields_def_field_materia',
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
  //   },
  //   dependencyId: 'idMateria'
  // },
];
