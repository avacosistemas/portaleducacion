import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const ALUMNO_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'nombre',
    labelKey: 'alumno_create_form_fields_def_field_nombre',
    label: 'Nombre',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100,
  },
  {
    key: 'apellido',
    labelKey: 'alumno_create_form_fields_def_field_apellido',
    label: 'Apellido',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100,
  },
  
  {
    key: 'idInstitucion',
    labelKey: 'alumno_create_form_fields_def_field_idinstitucion',
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
    key: 'username',
    labelKey: 'alumno_create_form_fields_def_field_username',
    label: 'Username',
    type: 'string',
    controlType: 'textbox',
    required: true,
    maxLength: 100,
  },
  {
    key: 'email',
    labelKey: 'alumno_create_form_fields_def_field_email',
    label: 'Email',
    type: 'email',
    controlType: 'email'
  },
  
  {
    key: 'tipoIdentificacion',
    labelKey: 'alumno_create_form_fields_def_field_tipoidentificacion',
    label: 'Tipo de Identificación',
    required:true,
    controlType: 'select',
    options: {
      handlerSourceData: false,
      elementLabel: 'nombre',
      elementValue: 'id',
      fromData: [{id: 'DNI', nombre: 'DNI'},
                 {id: 'CUIT', nombre: 'CUIT'},
                 {id: 'CUIL', nombre: 'CUIL'}
                 ]
    }
  },
  
  
  {
    key: 'numeroIdentificacion',
    labelKey: 'alumno_create_form_fields_def_field_numeroidentificacion',
    label: 'Número de Identificación',
    type: 'string',
    controlType: 'textbox'
  },
  {
    key: 'telefonoMovil',
    labelKey: 'alumno_create_form_fields_def_field_telefonomovil',
    label: 'Celular',
    type: 'string',
    controlType: 'textbox'
  },
  {
    key: 'telefonoFijo',
    labelKey: 'alumno_create_form_fields_def_field_telefonofijo',
    label: 'Teléfono Fijo',
    type: 'string',
    controlType: 'textbox'
  },
  {               
    key: 'foto',
    labelKey: 'Foto',
    controlType: 'file'
  }
];
