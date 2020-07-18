import { Routes } from '@angular/router';
import { AuthGuardService } from '../../../modules/fwk/core/service/security/auth-guard.service';
import { environment } from 'environments/environment';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { PRODUCTO_EXTERNO_DEF } from './producto_externo/producto_externo.def';
import { IntegrationComponent } from '../integration/integration.component';
import { PARAMETER_DEF } from './parameter/parameter.def';
import { INSTITUCIONES_DEF } from './instituciones/instituciones.def';
import { MATERIAS_DEF } from './materias/materias.def';
import { PROFESORES_DEF } from './profesores/profesores.def';
import { MATERIA_PROFESOR_DEF } from './materia_profesor/materia_profesor.def';
import { PERMISSION_DEF } from '../permission/component/permission.def';
import { PROFILE_DEF } from '../profile/component/profile.def';
import { ROLE_DEF } from '../role/component/role.def';
import { USER_DEF } from '../user/component/user.def';
import { HORAS_PROFESOR_DEF } from './horas_profesor/horas_profesor.def';


export const ROUTES: Routes = [  
    { 
      path: 'dashboard', 
      component: DashboardComponent, 
      canActivate: [AuthGuardService]
    },
    {
      path: PRODUCTO_EXTERNO_DEF.navigation.url.split('/')[1],
      component: IntegrationComponent,
      canActivate: [AuthGuardService]
    },
    {
      path: PARAMETER_DEF.navigation.url.split('/')[1],
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
    }

    
];


