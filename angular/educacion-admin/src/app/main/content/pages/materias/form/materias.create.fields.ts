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
    key: 'nivel',
    labelKey: 'materias_create_form_fields_def_field_idnivel',
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
      url: PREFIX_DOMAIN_API_EDUCACION + '/niveles/'
    }
  },

  { 
    key: 'idNivel',   
    controlType: 'hidden'
  },

  {
    key: 'descripcion',
    labelKey: 'materias_create_form_fields_def_field_descripcion',
    label: 'Descripcion',
    type: 'string',
    controlType: 'textbox',
    required:true
  }, 







];
