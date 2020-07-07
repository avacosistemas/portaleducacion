import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProfileComponent } from './component/profile.component';
import { ProfileService } from './service/profile.service';
import { FuseSharedModule } from '@fuse/shared.module';
import { FwkModule } from 'app/modules/fwk/core/fwk.module';



@NgModule({
  imports: [
    FuseSharedModule,
    FwkModule
  ],
  entryComponents: [ProfileComponent],
  declarations: [ProfileComponent],
  exports: [ProfileComponent],
  providers: [ProfileService],
})
export class ProfileModule { }
