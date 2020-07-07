
import { ComponentDef } from '../../../modules/fwk/core/model/component-def/component-def';
import { PRODUCTO_EXTERNO_DEF } from './producto_externo/producto_externo.def';
import { PARAMETER_DEF } from './parameter/parameter.def';
import { INSTITUCIONES_DEF } from './instituciones/instituciones.def';
import { MATERIAS_DEF } from './materias/materias.def';
import { USER_DEF } from '../user/component/user.def';
import { ROLE_DEF } from '../role/component/role.def';
import { PROFESORES_DEF } from './profesores/profesores.def';
import { PERMISSION_DEF } from '../permission/component/permission.def';

export const COMPONENTS: ComponentDef[] = [
  PRODUCTO_EXTERNO_DEF,
  PARAMETER_DEF,
  INSTITUCIONES_DEF,
  MATERIAS_DEF,
  USER_DEF,
  ROLE_DEF,
  PROFESORES_DEF,
  PERMISSION_DEF
];

