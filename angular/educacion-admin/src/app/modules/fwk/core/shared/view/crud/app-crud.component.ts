import { ViewEncapsulation, Component, OnInit, OnDestroy, Injector } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FuseConfigService } from '@fuse/services/config.service';
import { MatDialog } from '@angular/material';
import { CrudComponent } from '../../../component/crud/crud.component';
import { LocalStorageService } from '../../../service/local-storage/local-storage.service';



@Component({
  // tslint:disable-next-line:component-selector
  selector: 'crud',
  templateUrl: './app-crud.component.html',
  styleUrls: ['./app-crud.component.scss'],
  encapsulation: ViewEncapsulation.None,
  animations   : fuseAnimations
})
export class AppCrudComponent extends CrudComponent implements OnInit, OnDestroy {
 


  constructor(configService: FuseConfigService,
              dialog: MatDialog,
              localStorageService: LocalStorageService,
              injector: Injector) {
    super(configService, dialog, localStorageService, injector);
  }
  customFuseConfig() {

    return {
        layout: {
            theme_options: false
        }
    };
  }

  getParentTitle(){
    if (this.filterEntity != undefined && this.filterEntity['parentTitle']) {
      return " - " + this.filterEntity['parentTitle'];
    } return "";
  }

  goToLink(url: string){
    window.open(url, "_blank");
  }
}
