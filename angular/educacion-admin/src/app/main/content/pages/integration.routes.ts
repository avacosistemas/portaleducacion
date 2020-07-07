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
    }
];


