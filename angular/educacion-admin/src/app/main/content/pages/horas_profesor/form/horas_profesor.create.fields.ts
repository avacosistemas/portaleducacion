import { PREFIX_DOMAIN_API } from "environments/environment";

export const HORAS_PROFESOR_CREATE_FORM_FIELDS_DEF = [
  
          
        {
          key: 'id',
          labelKey: 'materia_profesor_create_form_fields_def_field_idprodfesor',
          label: 'id',
          type: 'hidden',
          mappingQuerystring: true,
          controlType: 'hidden'
       }, 
       
         {
          key: 'horaDesde',
          labelKey: 'seccion_create_form_fields_def_field_horaDesde',
          label: 'Hora Desde',
          controlType: 'textbox',
          type: 'text',
          maxLength: 200,
          required: true,
        },

        {
          key: 'horaHasta',
          labelKey: 'seccion_create_form_fields_def_field_horaHasta',
          label: 'Hora Hasta',
          controlType: 'textbox',
          type: 'text',
          maxLength: 200,
          required: true
        },          

        {
          key: 'lunes',
          labelKey: 'seccion_create_form_fields_def_field_lunes',
          label: 'Lunes ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'martes',
          labelKey: 'seccion_create_form_fields_def_field_martes',
          label: 'Martes ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'miercoles',
          labelKey: 'seccion_create_form_fields_def_field_miercoles',
          label: 'Miercoles ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'jueves',
          labelKey: 'seccion_create_form_fields_def_field_jueves',
          label: 'Jueves ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'viernes',
          labelKey: 'seccion_create_form_fields_def_field_viernes',
          label: 'Viernes ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'sabado ',
          labelKey: 'seccion_create_form_fields_def_field_sabado',
          label: 'Sabado ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        {
          key: 'domingo',
          labelKey: 'seccion_create_form_fields_def_field_domingo',
          label: 'Domingo ',
          controlType: 'checkbox',            
          required: false,
          disabled: false,
          value: false
        },
        /*
        {
          key: 'id',
          label: 'profesores_grid_def_column_id',
          controlType: 'hidden',            
          required: false,
          disabled: false          
        }
        */
       
  ];