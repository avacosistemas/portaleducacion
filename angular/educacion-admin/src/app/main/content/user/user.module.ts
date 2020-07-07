import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserComponent } from './component/user.component';
import { UserService } from './service/user.service';
import { FuseSharedModule } from '@fuse/shared.module';
import { FwkModule } from 'app/modules/fwk/core/fwk.module';



@NgModule({
  imports: [
    FuseSharedModule,
    FwkModule
    
  ],
  entryComponents: [UserComponent],
  declarations: [UserComponent],
  exports: [UserComponent],
  providers: [UserService],
})
export class UserModule { }
