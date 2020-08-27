export const SUBASTA_PRESTAMO_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'idSubasta',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_idsubasta',
    label: 'Id subasta',
    type: 'number',
    required: true,
    controlType: 'number'
  },
  {
    key: 'importeSolicitado',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_importesolicitado',
    label: 'Importe solicitado',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'cuotas',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_cuotas',
    label: 'Cuotas',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'destino',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_destino',
    label: 'Destino',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'estado',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_estado',
    label: 'Estado',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'tasaMinima',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_tasaminima',
    label: 'Tasa mínima',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'tasaMaxima',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_tasamaxima',
    label: 'Tasa máxima',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'nombreCliente',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_nombrecliente',
    label: 'Nombre Cliente',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'faltaReunir',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_faltareunir',
    label: 'Falta reunir',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'fechaInicioSubasta',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_fechainiciosubasta',
    label: 'Fecha inicio de subasta',
    type: 'datepicker',
    controlType: 'textbox'
  },
  {
    key: 'fechaFinSubasta',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_fechafinsubasta',
    label: 'Fecha fin de subasta',
    type: 'datepicker',
    controlType: 'textbox'
  },
  {
    key: 'montoAcumulado',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_montoacumulado',
    label: 'Monto acumulado',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'observacionesSolicitud',
    labelKey: 'subasta_prestamo_create_form_fields_def_field_observacionessolicitud',
    label: 'Observaciones solicitud',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  }
];
