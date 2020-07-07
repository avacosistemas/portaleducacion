import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { FuseConfigService } from '@fuse/services/config.service';
import { Injector } from '@angular/core';
import { RoleService } from '../service/role.service';
import { environment } from 'environments/environment';
import { AbstractComponent } from 'app/modules/fwk/core/component/abstract-component.component';


@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent extends AbstractComponent implements OnInit{
  
  constructor(public service: RoleService, injector: Injector) {
    super(injector);
  }
  
  getCrudName(){
    return 'role';
  }

  getI18nName(): string {
    return 'role';
  }

  onInit() {}
}
