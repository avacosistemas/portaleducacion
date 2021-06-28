import { Routes } from '@angular/router';
import { AuthGuardService } from '../../../modules/fwk/core/service/security/auth-guard.service';
import { environment } from 'environments/environment';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { IntegrationComponent } from '../integration/integration.component';
import { PARAMETER_DEF } from './parameter/parameter.def';
import { FAQS_DEF } from './faq/faqs.def';
import { INSTITUCIONES_DEF } from './instituciones/instituciones.def';
import { MATERIAS_DEF } from './materias/materias.def';
import { PROFESORES_DEF } from './profesores/profesores.def';
import { MATERIA_PROFESOR_DEF } from './materia_profesor/materia_profesor.def';
import { PERMISSION_DEF } from '../permission/component/permission.def';
import { PROFILE_DEF } from '../profile/component/profile.def';
import { ROLE_DEF } from '../role/component/role.def';
import { USER_DEF } from '../user/component/user.def';
import { HORAS_PROFESOR_DEF } from './horas_profesor/horas_profesor.def';
import { ALUMNO_DEF } from './alumno/alumno.def';
import { AULAS_DEF } from './aulas/aulas.def';
import { AULA_ALUMNO_DEF } from './aula_alumno/aula_alumno.def';
import { EVENTO_AULA_DEF } from './evento_aula/evento_aula.def';
import { UpdatePassword } from 'app/modules/fwk/core/model/resetpassword';
import { PasswordUpdateComponent } from '../authentication/password-update/password-update.component';
import { AULA_SOLICITUD_DEF } from './aula_solicitud/aula_solicitud.def';
import { AULA_SOLICITUD_FINALIZADA_DEF } from './aula_solicitud_finalizada/aula_solicitud_finalizada.def';


export const ROUTES: Routes = [
    {
      path: 'dashboard',
      component: DashboardComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: PARAMETER_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: FAQS_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: INSTITUCIONES_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: MATERIAS_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: PROFESORES_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: MATERIA_PROFESOR_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: PERMISSION_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: PROFILE_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: ROLE_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: USER_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: HORAS_PROFESOR_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: ALUMNO_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: AULAS_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: AULA_ALUMNO_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: AULA_SOLICITUD_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: AULA_SOLICITUD_FINALIZADA_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: EVENTO_AULA_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: environment.URL_PASSWORD_UPDATE,
      component: PasswordUpdateComponent
    }


];


