import { TEXTAREA } from "app/modules/fwk/core/model/dynamic-form/dynamic-field";
import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const AULA_SOLICITUD_GRID_DEF = {
  columnsDef: [
    {
      columnDef: 'id',
      columnNameKey: 'aula_solicitud_grid_def_column_id',
      key: true,
    },
    {
      columnDef: 'idAulaString',
      columnNameKey: 'aula_solicitud_grid_def_column_idaulastring'
    },
    {
      columnDef: 'nombreAlumno',
      columnNameKey: 'aula_solicitud_grid_def_column_nombrealumno'
    },
    {
      columnDef: 'nombreProfesor',
      columnNameKey: 'aula_solicitud_grid_def_column_nombreprofesor'
    },
    {
      columnDef: 'fechaHora',
      columnNameKey: 'aula_solicitud_grid_def_column_fechahora'
    },
    {
      columnDef: 'institucion',
      columnNameKey: 'aula_solicitud_grid_def_column_institucion'
    },
    {
      columnDef: 'documentoAlumno',
      columnNameKey: 'aula_solicitud_grid_def_column_documentoalumno'
    },
    {
      columnDef: 'materia',
      columnNameKey: 'aula_solicitud_grid_def_column_materia'
    }
  ],
  sortAllColumns: true,
  displayedColumns: [
    'institucion',
    'nombreAlumno',
    'documentoAlumno',
    'idAulaString',
    'nombreProfesor',
    'fechaHora',
    'materia'
  ],
  actions: [
    {
      actionNameKey: 'solicitud_aula_grid_def_button_action_aprobar',
      icon: 'check_circle_outline',
      confirm: true,
      ws: {
        key: 'solicitud_aula_grid_def_button_action_aprobar',
        url: PREFIX_DOMAIN_API_EDUCACION + '/solicitudAula/aprobar/',
        method: 'PUT'
      }
    },
    {
      actionNameKey: 'solicitud_aula_grid_def_button_action_rechazar',
      icon: 'highlight_off',
      formDef: {
        showSubmitContinue: true,
        fields:[
          {
            key: 'summary',
            labelKey: 'solicitud_aula_grid_def_button_action_rechazar_motivo',
            label: 'Motivo',
            controlType: TEXTAREA,
            maxLength: 8000
          }
        ]
      },
      ws: {
        key: 'solicitud_aula_grid_def_button_action_rechazar',
        url: PREFIX_DOMAIN_API_EDUCACION + '/solicitudAula/rechazar/',
        method: 'PUT'
      }
    },
  ]
};
