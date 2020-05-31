import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FuseSharedModule } from '@fuse/shared.module';

import { FuseContentComponent } from 'app/main/content/content.component';
import { MatSidenavModule } from '@angular/material';
import { FuseThemeOptionsModule, FuseNavigationModule, FuseSearchBarModule, FuseShortcutsModule, FuseSidebarModule } from '@fuse/components';
import { FuseFooterModule } from '../footer/footer.module';
import { FuseNavbarModule } from '../navbar/navbar.module';
import { FuseQuickPanelModule } from '../quick-panel/quick-panel.module';
import { FuseToolbarModule } from '../toolbar/toolbar.module';
import { LoginModule } from './authentication/login/login.module';
import { IntegrationModule } from './integration/integration.module';

@NgModule({
    declarations: [
        FuseContentComponent
    ],
    imports     : [
        RouterModule,
        FuseSharedModule,
        RouterModule,
        MatSidenavModule,
        FuseThemeOptionsModule,
        FuseNavigationModule,
        FuseSearchBarModule,
        FuseShortcutsModule,
        FuseSidebarModule,
        FuseFooterModule,
        FuseNavbarModule,
        FuseQuickPanelModule,
        FuseToolbarModule,
        LoginModule,
        IntegrationModule
    ],
    exports: [
        FuseContentComponent
    ],
    providers: []
})
export class FuseContentModule
{
}
