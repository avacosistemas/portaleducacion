export const HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF = [
  {
    key: 'dia',
    labelKey: 'HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF_FIELD_dia',
    label: 'Día',
    controlType: 'select',
    options: {
      handlerSourceData: false,
      elementLabel: 'nombre',
      elementValue: 'id',
      fromData: [{id: 'LUNES', nombre: 'LUNES'},
                 {id: 'MARTES', nombre: 'MARTES'},
                 {id: 'MIÉRCOLES', nombre: 'MIÉRCOLES'},
                 {id: 'JUEVES', nombre: 'JUEVES'},
                 {id: 'VIERNES', nombre: 'VIERNES'},
                 {id: 'SÁBADO', nombre: 'SÁBADO'},
                 {id: 'DOMINGO', nombre: 'DOMINGO'}
                 ]
    }
  },
  {
    key: 'hora',
    labelKey: 'HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF_FIELD_hora',
    label: 'Hora',
    type: 'string',
    controlType: 'textbox'
  },
  {
    key: 'id',
    labelKey: 'HORAS_PROFESOR_FILTER_FORM_FIELDS_DEF_FIELD_id',
    label: 'ID',
    type: 'hidden',
    controlType: 'hidden'
  },
  {
    key : 'parentTitle',
    controlType: 'hidden'
  }
];
