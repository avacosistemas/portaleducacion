import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule, MatIconModule, MatMenuModule, MatProgressBarModule, MatToolbarModule } from '@angular/material';

import { FuseSharedModule } from '@fuse/shared.module';

import { FuseToolbarComponent } from 'app/main/toolbar/toolbar.component';
import { FuseSearchBarModule, FuseShortcutsModule } from '@fuse/components';
import { BackButtonComponent } from 'app/modules/fwk/core/component/back-button/backbutton.component';

@NgModule({
    declarations: [
        FuseToolbarComponent,
        BackButtonComponent
    ],
    imports     : [
        RouterModule,

        MatButtonModule,
        MatIconModule,
        MatMenuModule,
        MatProgressBarModule,
        MatToolbarModule,

        FuseSharedModule,
        FuseSearchBarModule,
        FuseShortcutsModule,
    ],
    exports     : [
        FuseToolbarComponent
    ],
    entryComponents: [BackButtonComponent]
})
export class FuseToolbarModule
{
}
