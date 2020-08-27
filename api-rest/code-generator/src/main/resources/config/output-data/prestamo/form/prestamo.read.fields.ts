export const PRESTAMO_READ_FORM_FIELDS_DEF = [
  {
    key: 'idPrestamo',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_idprestamo',
    label: 'Id préstamo',
    type: 'number',
    required: true,
    disabled: true,
    controlType: 'number'
  },
  {
    key: 'importeSolicitado',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_importesolicitado',
    label: 'Importe solicitado',
    type: 'number',
    disabled: true,
    controlType: 'number'
  },
  {
    key: 'cuotas',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_cuotas',
    label: 'Cuotas',
    type: 'number',
    disabled: true,
    controlType: 'number'
  },
  {
    key: 'estado',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_estado',
    label: 'Estado',
    type: 'string',
    disabled: true,
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'nombreCliente',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_nombrecliente',
    label: 'Nombre Cliente',
    type: 'string',
    disabled: true,
    controlType: 'textarea',
    maxLength: 255
  },
  {
    key: 'tasa',
    labelKey: 'PRESTAMO_READ_FORM_FIELDS_DEF_FIELD_tasa',
    label: 'Tasa',
    type: 'number',
    disabled: true,
    controlType: 'number'
  }
];
