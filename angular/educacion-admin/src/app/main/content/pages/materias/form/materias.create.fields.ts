import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const MATERIAS_CREATE_FORM_FIELDS_DEF = [
  /*
  {
    key: 'idNivel',
    labelKey: 'materias_create_form_fields_def_field_idnivel',
    label: 'Nivel',
    type: 'string',
    controlType: 'textbox',
    required:true
  },
 */
  /*
  {
    key: 'idNivel',
    labelKey: 'materias_create_form_fields_def_field_idnivel',
    label: 'Nivel',
    type: 'select',
    controlType: 'textbox',
    required:true
  },
  */
  
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
  },
  dependencyKey: 'idMateria'
},
{
  key: 'idMateria',
  labelKey: 'materias_create_form_fields_def_field_descripcion',
  required: true,
  controlType: 'select',
  options: {
      elementLabel: 'descripcion',
      elementValue: 'id',
      fromWs: {
        key: 'materias_create_form_fields_def_field_descripcion',
        url: PREFIX_DOMAIN_API_EDUCACION + '/materias/nivel/'
      }
  },
  stopLoad: true
},
  // {
  //   key: 'nivel',
  //   labelKey: 'materias_create_form_fields_def_field_idnivel',
  //   controlType: 'autocomplete',
  //   label: 'Nivel',
  //   required: true,
  //   options: {
  //     transferIdToField: 'idNivel',
  //     elementLabel: 'descripcion',
  //     elementValue: 'id',
  //     useNativeFilter: false,
  //     selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
  //   },
  //   apiOptions: {
  //     queryString: {
  //       descripcion: 'nivel'
  //     },
  //     url: PREFIX_DOMAIN_API_EDUCACION + '/niveles/'
  //   }
  // },

  // { 
  //   key: 'idNivel',   
  //   controlType: 'hidden'
  // },

  // {
  //   key: 'descripcion',
  //   labelKey: 'materias_create_form_fields_def_field_descripcion',
  //   label: 'Descripcion',
  //   type: 'string',
  //   controlType: 'textbox',
  //   required:true,
  //   maxLenght: 100
  // }, 







];
