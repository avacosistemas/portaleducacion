
import { ComponentDef } from '../../../modules/fwk/core/model/component-def/component-def';
import { PARAMETER_DEF } from './parameter/parameter.def';
import { FAQS_DEF } from './faq/faqs.def';
import { INSTITUCIONES_DEF } from './instituciones/instituciones.def';
import { MATERIAS_DEF } from './materias/materias.def';
import { USER_DEF } from '../user/component/user.def';
import { ROLE_DEF } from '../role/component/role.def';
import { PROFESORES_DEF } from './profesores/profesores.def';
import { PERMISSION_DEF } from '../permission/component/permission.def';
import { MATERIA_PROFESOR_DEF } from './materia_profesor/materia_profesor.def';
import { HORAS_PROFESOR_DEF } from './horas_profesor/horas_profesor.def';
import { ALUMNO_DEF } from './alumno/alumno.def';
import { AULAS_DEF } from './aulas/aulas.def';
import { AULA_ALUMNO_DEF } from './aula_alumno/aula_alumno.def';
import { PROFILE_DEF } from '../profile/component/profile.def';
import { EVENTO_AULA_DEF } from './evento_aula/evento_aula.def';
import { AULA_SOLICITUD_DEF } from './aula_solicitud/aula_solicitud.def';
import { AULA_SOLICITUD_FINALIZADA_DEF } from './aula_solicitud_finalizada/aula_solicitud_finalizada.def';

export const COMPONENTS: ComponentDef[] = [
  PARAMETER_DEF,
  FAQS_DEF,
  INSTITUCIONES_DEF,
  MATERIAS_DEF,
  USER_DEF,
  ROLE_DEF,
  PROFESORES_DEF,
  PERMISSION_DEF,
  PROFILE_DEF,
  MATERIA_PROFESOR_DEF,
  HORAS_PROFESOR_DEF,
  ALUMNO_DEF,
  AULAS_DEF,
  AULA_ALUMNO_DEF,
  EVENTO_AULA_DEF,
  AULA_SOLICITUD_DEF,
  AULA_SOLICITUD_FINALIZADA_DEF
];

