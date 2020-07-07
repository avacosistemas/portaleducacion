import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PermissionComponent } from './component/permission.component';
import { PermissionService } from './service/permission.service';
import { FuseSharedModule } from '@fuse/shared.module';
import { FwkModule } from 'app/modules/fwk/core/fwk.module';

@NgModule({
  imports: [        
    FuseSharedModule,
    FwkModule
  ],
  entryComponents: [PermissionComponent],
  declarations: [PermissionComponent],
  exports: [PermissionComponent],
  providers: [PermissionService],
})
export class PermissionModule { }
