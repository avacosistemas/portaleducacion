import { PREFIX_DOMAIN_API } from "environments/environment";

export const PROFESORES_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'profesores_grid_def_column_id'
    },
    {
      columnDef: 'nombreApellido',
      columnNameKey: 'profesores_grid_def_column_nombreapellido'
    },
    {
      columnDef: 'tipoIdentificacion',
      columnNameKey: 'profesores_grid_def_column_tipoidentificacion'
    },
    {
      columnDef: 'numeroIdentificacion',
      columnNameKey: 'profesores_grid_def_column_numeroidentificacion'
    },
    {
      columnDef: 'username',
      columnNameKey: 'profesores_grid_def_column_username'
    },
    {
      columnDef: 'email',
      columnNameKey: 'profesores_grid_def_column_email'
    },
    {
      columnDef: 'telefonoMovil',
      columnNameKey: 'profesores_grid_def_column_telefonomovil'
    }
  ],
  sortAllColumns: true,
  deleteAction: true, 
  displayedColumns: [
    'id',
    'nombreApellido',
    'tipoIdentificacion',
    'numeroIdentificacion',
    'username',
    'email',
    'telefonoMovil'
  ],
  actions: [
   
    {
      actionNameKey: 'profesores_grid_def_button_action_nuevo_horario',
      icon: 'post_add',
      formDef: {
        showSubmitContinue: false,
        fields : [
          /*
          {
            label: 'id',
            key: 'id',
            controlType: 'hidden'
          }, 
          */  
          {
            key: 'horaDesde',
            labelKey: 'seccion_create_form_fields_def_field_horaDesde',
            label: 'Hora Desde',
            controlType: 'textbox',
            maxLength: 200,
            required: true,
          },

          {
            key: 'horaHasta',
            labelKey: 'seccion_create_form_fields_def_field_horaHasta',
            label: 'Hora Hasta',
            controlType: 'textbox',
            maxLength: 200,
            required: true
          },          

          {
            key: 'lunes',
            label: 'Lunes ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'martes',
            label: 'Martes ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'miercoles',
            label: 'Miercoles ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'jueves',
            label: 'Jueves ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'viernes',
            label: 'Viernes ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'sabado ',
            label: 'Sabado ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'domingo',
            label: 'Domingo ',
            controlType: 'checkbox',            
            required: false,
            disabled: false,
            value: false
          },
          {
            key: 'id',
            label: 'profesores_grid_def_column_id',
            controlType: 'hidden',            
            required: false,
            disabled: false          
          }        
        ]
      },
      ws: {
        key: 'profesores_grid_def_button_action_nuevo_horario',
        url: PREFIX_DOMAIN_API + 'ws-rest-educacion/horariosdisponibles/',
        method: 'POST'
      }
    },

    {
      actionNameKey: 'profesores_grid_def_button_action_habilitar',
      icon: 'thumb_up',
      ws: {
        key: 'profesores_grid_def_button_action_habilitar',
        url: PREFIX_DOMAIN_API + 'ws-rest-educacion/profesores/habilitar/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    },

    {
      actionNameKey: 'profesores_grid_def_button_action_bloquear',
      icon: 'thumb_down',
      ws: {
        key: 'profesores_grid_def_button_action_bloquear',
        url: PREFIX_DOMAIN_API + 'ws-rest-educacion/profesores/bloquear/',
        method: 'PUT',
        querystring: {
          id : 'id'
        }
      }
    }

  ]
};
