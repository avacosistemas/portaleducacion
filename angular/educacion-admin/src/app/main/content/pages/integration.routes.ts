import { Routes } from '@angular/router';
import { AuthGuardService } from '../../../modules/fwk/core/service/security/auth-guard.service';
import { environment } from 'environments/environment';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { PRODUCTO_EXTERNO_DEF } from './producto_externo/producto_externo.def';
import { IntegrationComponent } from '../integration/integration.component';

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
    }
];


