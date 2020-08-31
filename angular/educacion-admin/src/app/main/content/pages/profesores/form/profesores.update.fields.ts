export const PROFESORES_UPDATE_FORM_FIELDS_DEF = [
  {
    key: 'id',
    labelKey: 'PROFESORES_UPDATE_FORM_FIELDS_DEF_FIELD_id',
    label: 'ID',
    type: 'hidden',
    controlType: 'hidden'
  },
  {
    key: 'nombre',
    labelKey: 'profesores_create_form_fields_def_field_nombre',
    label: 'Nombre y Apellido',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 100
  },
  {
    key: 'apellido',
    labelKey: 'profesores_create_form_fields_def_field_apellido',
    label: 'Nombre y Apellido',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 100
  },
  {
    key: 'tipoIdentificacion',
    labelKey: 'profesores_create_form_fields_def_field_tipoidentificacion',
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
    labelKey: 'profesores_create_form_fields_def_field_numeroidentificacion',
    label: 'Número de Identificación',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 12
  },
  {
    key: 'username',
    labelKey: 'profesores_create_form_fields_def_field_username',
    label: 'Usuario',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 100
  },
  {
    key: 'email',
    labelKey: 'profesores_create_form_fields_def_field_email',
    label: 'Email',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 250
  },
  {
    key: 'telefonoMovil',
    labelKey: 'profesores_create_form_fields_def_field_telefonomovil',
    label: 'Celular',
    type: 'string',
    controlType: 'textbox',
    required:true,
    maxLength: 15
  },
  {
    key: 'titulo',
    labelKey: 'profesores_create_form_fields_def_field_titulo',
    controlType: 'textbox',
    maxLength: 100
  },
  {
    key: 'descripcion',
    labelKey: 'profesores_create_form_fields_def_field_descripcion',
    controlType: 'textbox',
    maxLength: 1000
  },
  {
    key: 'valorHora',
    labelKey: 'profesores_create_form_fields_def_field_valorhora',
    controlType: 'number',
    min: 0
  },
  {               
    key: 'foto',
    labelKey: 'Foto (solo jpg)',
    controlType: 'file'
  },
  {               
    key: 'tieneFoto',
    labelKey: 'Foto Cargada',
    controlType: 'check',
    readonly: true
  }
];
