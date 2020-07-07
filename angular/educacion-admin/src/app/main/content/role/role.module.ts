import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { RoleComponent } from './component/role.component';
import { RoleService } from './service/role.service';
import { FuseSharedModule } from '@fuse/shared.module';
import { FwkModule } from 'app/modules/fwk/core/fwk.module';

@NgModule({
  imports: [
    FuseSharedModule,
    FwkModule
  ],
  entryComponents: [RoleComponent],
  declarations: [RoleComponent],
  exports: [RoleComponent],
  providers: [RoleService],
})
export class RoleModule { }
