import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { FuseConfigService } from '@fuse/services/config.service';
import { Injector } from '@angular/core';

import { PermissionService } from '../service/permission.service';
import { PERMISSION_SEARCH_FIELDS } from '../form/search.fields';
import { environment } from 'environments/environment';
import { AbstractComponent } from 'app/modules/fwk/core/component/abstract-component.component';


@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent extends AbstractComponent implements OnInit{
  
  constructor(public service: PermissionService, injector: Injector) {
    super(injector);
  }
  
  getI18nName(): string {
    return 'permission';
  }

  onInit() {}
}
