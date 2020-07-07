import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { FuseConfigService } from '@fuse/services/config.service';
import { Injector } from '@angular/core';
import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';

import { environment } from 'environments/environment';
import { UserService } from '../service/user.service';
import { ProfileService } from '../../profile/service/profile.service';
import { USER_SEARCH_FIELDS } from '../form/search.fields';
import { AbstractComponent } from 'app/modules/fwk/core/component/abstract-component.component';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent extends AbstractComponent implements OnInit{
  
  constructor(public service: UserService,
              injector: Injector) {
    super(injector);
  }
  
  getCrudName(){
    return 'user';
  }

  getI18nName(): string {
    return 'user';
  }

  handlerFieldSourceData(keyComponent: string, entity: any, injector: Injector){
    switch (keyComponent){
      // case PROFILES_COMPONENT_KEY: return injector.get(ProfileService).findAll();
    }
    return of();
  }

  onInit() {}
}
