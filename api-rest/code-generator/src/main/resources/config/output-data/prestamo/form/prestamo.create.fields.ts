export const PRESTAMO_CREATE_FORM_FIELDS_DEF = [
  {
    key: 'idPrestamo',
    labelKey: 'prestamo_create_form_fields_def_field_idprestamo',
    label: 'Id préstamo',
    type: 'number',
    required: true,
    controlType: 'number'
  },
  {
    key: 'importeSolicitado',
    labelKey: 'prestamo_create_form_fields_def_field_importesolicitado',
    label: 'Importe solicitado',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'cuotas',
    labelKey: 'prestamo_create_form_fields_def_field_cuotas',
    label: 'Cuotas',
    type: 'number',
    controlType: 'number'
  },
  {
    key: 'estado',
    labelKey: 'prestamo_create_form_fields_def_field_estado',
    label: 'Estado',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'nombreCliente',
    labelKey: 'prestamo_create_form_fields_def_field_nombrecliente',
    label: 'Nombre Cliente',
    type: 'string',
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'tasa',
    labelKey: 'prestamo_create_form_fields_def_field_tasa',
    label: 'Tasa',
    type: 'number',
    controlType: 'number'
  }
];
